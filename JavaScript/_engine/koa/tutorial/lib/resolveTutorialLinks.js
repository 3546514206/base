'use strict';

/**
 * Parser plugin
 * For links info:articleSlug & info:task/taskSlug
 * Load titles from db
 */
const assert = require('assert');

assert(typeof IS_CLIENT === 'undefined');

const TutorialTree = require('../models/tutorialTree');

const Token = require('engine/markit').Token;
const t = require('engine/i18n');
const url = require('url');
const tokenUtils = require('engine/markit').tokenUtils;

module.exports = async function (tokens) {

    let isEmptyLink, isAutoLink;
    for (let idx = 0; idx < tokens.length; idx++) {
        let token = tokens[idx];

        if (token.type != 'inline' || !token.children) continue;
        for (let i = 0; i < token.children.length; i++) {
            let inlineToken = token.children[i];

            if (inlineToken.type == 'link_open') {
                let href = tokenUtils.attrGet(inlineToken, 'href');
                if (!href.startsWith('info:')) continue;

                let urlParsed = url.parse(href.slice(5));
                let pathname = urlParsed.pathname;

                isEmptyLink = token.children[i + 1].type == 'link_close';
                isAutoLink = token.children[i + 1].type == 'text' &&
                    token.children[i + 1].content == href &&
                    token.children[i + 2].type == 'link_close';

                if (pathname.startsWith('task/')) {
                    let task = TutorialTree.instance().bySlug(pathname.slice('task/'.length));
                    if (task) replaceLink(token.children, i, task.title, task.getUrl(), urlParsed);
                    else replaceLinkWithError(token.children, i, t('tutorial.task.task_not_found', {path: pathname}));
                } else {
                    let article = TutorialTree.instance().bySlug(pathname);
                    if (article) replaceLink(token.children, i, article.title, article.getUrl(), urlParsed);
                    else replaceLinkWithError(token.children, i, t('tutorial.article.article_not_found', {path: pathname}));
                }

            }
        }

    }

    function replaceLinkWithError(children, linkOpenIdx, content) {
        let token = new Token('markdown_error_inline', '', 0);
        token.content = content;
        token.level = children[linkOpenIdx].level;

        let linkCloseIdx = linkOpenIdx + 1;
        while (children[linkCloseIdx].type != 'link_close') linkCloseIdx++;
        children.splice(linkOpenIdx, linkCloseIdx - linkOpenIdx + 1, token);
    }


    function replaceLink(children, linkOpenIdx, title, url, prevUrlParsed) {
        if (prevUrlParsed.query) url += '?' + prevUrlParsed.query;
        if (prevUrlParsed.hash) url += prevUrlParsed.hash;

        tokenUtils.attrReplace(children[linkOpenIdx], 'href', url);

        // for empty & autolinks also replace children
        if (isEmptyLink) {
            let token = new Token('text', '', 0);
            token.content = title;
            token.level = children[linkOpenIdx].level;
            children.splice(linkOpenIdx + 1, 0, token);
        } else if (isAutoLink) {
            children[linkOpenIdx + 1].content = title;
        }
    }

};


