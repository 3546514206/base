'use strict';


/**
 * Reads ## Heading [#anchor]
 * Transliterates heading text if no anchor
 * writes that to `headingToken.anchor`
 *
 * Renders as link if `options.linkHeaderTag` or header id
 */

const parseAttrs = require('../utils/parseAttrs');
const makeAnchor = require('engine/text-utils/makeAnchor');
const escapeHtml = require('escape-html');

const t = require('engine/i18n');

t.requirePhrase('engine/markit');

// add headingToken.achor
// not "id" attr, because rendering uses `.anchor` for the extra link OR id
function readHeadingAnchor(state) {

    let env = state.env;
    if (!env.headingsMap) env.headingsMap = new Map();

    let tokens = state.tokens;
    for (let idx = 0; idx < state.tokens.length; idx++) {
        let headingToken = state.tokens[idx];

        if (headingToken.type !== 'heading_open') continue;

        idx++;

        let inlineToken = tokens[idx];
        if (inlineToken.type != 'inline') continue;

        let anchorReg = /\s+\[#(.*?)\]$/;
        let anchor;
        if (inlineToken.content.match(anchorReg)) {
            anchor = inlineToken.content.match(anchorReg)[1];

            // strip [#...] from token content
            inlineToken.content = inlineToken.content.replace(anchorReg, '');
            let lastTextToken = inlineToken.children[inlineToken.children.length - 1];
            lastTextToken.content = inlineToken.content.replace(anchorReg, '');

            if (env.headingsMap.has(anchor)) {
                // fixed anchor, cannot change, add -1 -2
                lastTextToken.type = 'markdown_error_inline';
                lastTextToken.content = t('markit.error.anchor_exists', {anchor: 'anchor'});
            } else {
                env.headingsMap.set(anchor, 1);
            }

        } else {
            anchor = makeAnchor(inlineToken.content);

            if (env.headingsMap.has(anchor)) {
                // иначе просто добавляю -2, -3 ...
                env.headingsMap.set(anchor, env.headingsMap.get(anchor) + 1);
                anchor = anchor + '-' + env.headingsMap.get(anchor);
            } else {
                env.headingsMap.set(anchor, 1);
            }

        }

        headingToken.anchor = anchor;

    }

}

module.exports = function (md) {

    md.core.ruler.push('read_heading_anchor', readHeadingAnchor);

    md.renderer.rules.heading_open = function (tokens, idx, options, env, slf) {
        let token = tokens[idx];
        let anchor = token.anchor;
        if (options.linkHeaderTag) {
            return `<${token.tag}${slf.renderAttrs(token)}><a class="main__anchor" name="${anchor}" href="#${anchor}">`;
        } else {
            // for ebook need id
            return `<${token.tag} id="${anchor}">`;
        }
    };

    md.renderer.rules.heading_close = function (tokens, idx, options, env, slf) {
        let token = tokens[idx];
        if (options.linkHeaderTag) {
            return `</a></${token.tag}>`;
        } else {
            return `</${token.tag}>`;
        }
    };

};
