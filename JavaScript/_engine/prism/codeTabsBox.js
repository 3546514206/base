let delegate = require('client/delegate');
let makeLineNumbers = require('./makeLineNumbers');

class CodeTabsBox {
    constructor(elem) {
        if (window.ebookType) {
            return;
        }

        this.elem = elem;
        this.translateX = 0;

        this.switchesElem = elem.querySelector('[data-code-tabs-switches]');
        this.switchesElemItems = this.switchesElem.firstElementChild;
        this.arrowLeft = elem.querySelector('[data-code-tabs-left]');
        this.arrowRight = elem.querySelector('[data-code-tabs-right]');


        this.arrowLeft.onclick = (e) => {
            e.preventDefault();

            this.translateX = Math.max(0, this.translateX - this.switchesElem.offsetWidth);
            this.renderTranslate();
        };


        this.arrowRight.onclick = (e) => {
            e.preventDefault();

            this.translateX = Math.min(this.translateX + this.switchesElem.offsetWidth, this.switchesElemItems.offsetWidth - this.switchesElem.offsetWidth);
            this.renderTranslate();
        };

        let tabCurrent = this.elem.querySelector('.code-tabs__section_current');
        if (tabCurrent !== tabCurrent.parentElement.firstElementChild) { // if not result tab
            this.highlightTab(tabCurrent);
        }

        this.delegate('.code-tabs__switch', 'click', this.onSwitchClick);
    }

    onSwitchClick(e) {
        e.preventDefault();

        let siblings = e.delegateTarget.parentNode.children;
        let tabs = this.elem.querySelector('[data-code-tabs-content]').children;


        let selectedIndex;
        for (let i = 0; i < siblings.length; i++) {
            let switchElem = siblings[i];
            let tabElem = tabs[i];
            if (switchElem === e.delegateTarget) {
                selectedIndex = i;
                tabElem.classList.add('code-tabs__section_current');
                switchElem.classList.add('code-tabs__switch_current');
            } else {
                tabElem.classList.remove('code-tabs__section_current');
                switchElem.classList.remove('code-tabs__switch_current');
            }
        }

        if (selectedIndex === 0) {
            this.elem.classList.add('code-tabs_result_on');
        } else {
            this.elem.classList.remove('code-tabs_result_on');

            this.highlightTab(tabs[selectedIndex]);
        }

    }

    highlightTab(tab) {
        if (tab.highlighted) return;
        let preElem = tab.querySelector('pre');
        let codeElem = preElem.querySelector('code');
        Prism.highlightElement(codeElem);

        preElem.insertAdjacentHTML("afterBegin", makeLineNumbers(preElem.innerHTML));

        tab.highlighted = true;
    }

    renderTranslate() {
        this.switchesElemItems.style.transform = 'translateX(-' + this.translateX + 'px)';
        if (this.translateX === 0) {
            this.arrowLeft.setAttribute('disabled', '');
        } else {
            this.arrowLeft.removeAttribute('disabled');
        }

        if (this.translateX === this.switchesElemItems.offsetWidth - this.switchesElem.offsetWidth) {
            this.arrowRight.setAttribute('disabled', '');
        } else {
            this.arrowRight.removeAttribute('disabled');
        }

    }
}


delegate.delegateMixin(CodeTabsBox.prototype);


module.exports = CodeTabsBox;
