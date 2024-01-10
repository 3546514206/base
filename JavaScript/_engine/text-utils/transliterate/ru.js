// Transliteration ported from https://github.com/yaroslav/russian/blob/master/lib/russian/transliteration.rb

let LOWER_SINGLE = {
    "і": "i", "ґ": "g", "ё": "yo", "№": "#", "є": "e",
    "ї": "yi", "а": "a", "б": "b",
    "в": "v", "г": "g", "д": "d", "е": "e", "ж": "zh",
    "з": "z", "и": "i", "й": "y", "к": "k", "л": "l",
    "м": "m", "н": "n", "о": "o", "п": "p", "р": "r",
    "с": "s", "т": "t", "у": "u", "ф": "f", "х": "h",
    "ц": "ts", "ч": "ch", "ш": "sh", "щ": "sch", "ъ": "",
    "ы": "y", "ь": "", "э": "e", "ю": "yu", "я": "ya"
};

let LOWER_MULTI = {
    "ье": "ie",
    "ьё": "ie"
};

let UPPER_SINGLE = {
    "Ґ": "G", "Ё": "YO", "Є": "E", "Ї": "YI", "І": "I",
    "А": "A", "Б": "B", "В": "V", "Г": "G",
    "Д": "D", "Е": "E", "Ж": "ZH", "З": "Z", "И": "I",
    "Й": "Y", "К": "K", "Л": "L", "М": "M", "Н": "N",
    "О": "O", "П": "P", "Р": "R", "С": "S", "Т": "T",
    "У": "U", "Ф": "F", "Х": "H", "Ц": "TS", "Ч": "CH",
    "Ш": "SH", "Щ": "SCH", "Ъ": "", "Ы": "Y", "Ь": "",
    "Э": "E", "Ю": "YU", "Я": "YA"
};
let UPPER_MULTI = {
    "ЬЕ": "IE",
    "ЬЁ": "IE"
};

let LOWER = Object.assign({}, LOWER_SINGLE, LOWER_MULTI);

let UPPER = Object.assign({}, UPPER_SINGLE, UPPER_MULTI);

let MULTI_KEYS = Object.keys(Object.assign({}, LOWER_MULTI, UPPER_MULTI)).sort(function (a, b) {
    return a.length > b.length;
});


// Transliterate a string with russian/ukrainian characters
function transliterate(str) {
    let reg = new RegExp(MULTI_KEYS.join('|') + '|\\w|.', 'g');

    let result = "";
    let chars = str.match(reg) || "";
    for (let i = 0; i < chars.length; i++) {
        if (chars[i] in UPPER && chars[i + 1] in LOWER) {
            // combined case
            let r = UPPER[chars[i]].toLowerCase();
            result += r[0].toUpperCase() + r.slice(1);
        } else if (chars[i] in UPPER) {
            result += UPPER[chars[i]];
        } else if (chars[i] in LOWER) {
            result += LOWER[chars[i]];
        } else {
            result += chars[i];
        }
    }

    return result;
}

module.exports = transliterate;
