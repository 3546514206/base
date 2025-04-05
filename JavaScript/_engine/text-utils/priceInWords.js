// borrowed from http://javascript.ru/forum/misc/40642-summa-propisyu-2.html

module.exports = priceInWords;

function priceInWords(number, addCurrency = true, locale = 'ru') {

    return (locale == 'ru' ? ru(number, addCurrency) : ua(number, addCurrency)).trim();
};

function ru(_number, addCurrency) {
    if (!_number) return 'Ноль' + (addCurrency ? ' рублей' : '');

    let _arr_numbers = new Array();
    _arr_numbers[1] = new Array('', 'один', 'два', 'три', 'четыре', 'пять', 'шесть', 'семь', 'восемь', 'девять', 'десять', 'одиннадцать', 'двенадцать', 'тринадцать', 'четырнадцать', 'пятнадцать', 'шестнадцать', 'семнадцать', 'восемнадцать', 'девятнадцать');
    _arr_numbers[2] = new Array('', '', 'двадцать', 'тридцать', 'сорок', 'пятьдесят', 'шестьдесят', 'семьдесят', 'восемьдесят', 'девяносто');
    _arr_numbers[3] = new Array('', 'сто', 'двести', 'триста', 'четыреста', 'пятьсот', 'шестьсот', 'семьсот', 'восемьсот', 'девятьсот');

    function number_parser(_num, _desc) {
        let _string = '';
        let _num_hundred = '';
        if (_num.length == 3) {
            _num_hundred = _num.substr(0, 1);
            _num = _num.substr(1, 3);
            _string = _arr_numbers[3][_num_hundred] + ' ';
        }
        if (_num < 20) _string += _arr_numbers[1][parseFloat(_num)] + ' ';
        else {
            let _first_num = _num.substr(0, 1);
            let _second_num = _num.substr(1, 2);
            _string += _arr_numbers[2][_first_num] + ' ' + _arr_numbers[1][_second_num] + ' ';
        }
        let last, _last_num, _slice_num, _pre_last_num;

        switch (_desc) {
            case 0:
                last = _num.length - 1;
                _last_num = parseFloat(_num.charAt(last));
                _slice_num = _num.slice(0, -1);
                _pre_last_num = parseFloat(_slice_num);

                if (addCurrency) {
                    if (_last_num == 1 && 1 != _pre_last_num) _string += 'рубль';
                    else if (_last_num > 1 && _last_num < 5 && 1 != _pre_last_num) _string += 'рубля';
                    else if ("" != _slice_num) _string += 'рублей';
                    else if (1 == _pre_last_num) _string += 'рублей';
                    else if ("" != _slice_num && _last_num > 4) _string += 'рублей';
                    else if ("" == _slice_num && _last_num > 4) _string += 'рублей';
                    else if ("" == _slice_num && 0 == _last_num) _string += 'Ноль рублей';
                    else _string += 'рубль';
                }
                break;

            case 1:
                last = _num.length - 1;
                _last_num = parseFloat(_num.charAt(last));
                _slice_num = _num.slice(0, -1);
                _pre_last_num = parseFloat(_slice_num);
                if (_last_num == 1 && 1 != _pre_last_num) _string += 'тысяча ';
                else if (_last_num == 1 && 1 == _pre_last_num.toString().length) _string += 'тысяча ';
                else if (_last_num > 1 && _last_num < 5 && 1 != _pre_last_num) _string += 'тысячи ';
                else if (parseFloat(_num) != 0) _string += 'тысяч ';
                _string = _string.replace('один ', 'одна ');
                _string = _string.replace('два ', 'две ');
                break;

            case 2:
                _last_num = parseFloat(_num.substr(-1));
                last = _num.length - 1;
                _last_num = parseFloat(_num.charAt(last));
                _slice_num = _num.slice(0, -1);
                _pre_last_num = parseFloat(_slice_num);
                if (_last_num == 1 && 1 != _pre_last_num) _string += 'миллион ';
                else if (_last_num == 1 && 1 == _pre_last_num.toString().length) _string += 'миллион ';
                else if (_last_num > 1 && _last_num < 5 && 1 != _pre_last_num) _string += 'миллиона ';
                else _string += 'миллионов ';
                break;
            case 3:
                _last_num = parseFloat(_num.substr(-1));
                last = _num.length - 1;
                _last_num = parseFloat(_num.charAt(last));
                _slice_num = _num.slice(0, -1);
                _pre_last_num = parseFloat(_slice_num);
                if (_last_num == 1 && 1 != _pre_last_num) _string += 'миллиард ';
                else if (_last_num == 1 && 1 == _pre_last_num.toString().length) _string += 'миллиард ';
                else if (_last_num > 1 && _last_num < 5 && 1 != _pre_last_num) _string += 'миллиарда ';
                else _string += 'миллиардов ';
                break;
        }
        _string = _string.replace('  ', ' ');
        return _string;
    }

    if (typeof _number !== 'number') {
        _number = _number.replace(',', '.');
        _number = parseFloat(_number);
        if (isNaN(_number)) return 'Ноль' + (addCurrency ? ' рублей' : '');
    }
    _number = String(_number);

    if (_number.indexOf('.') != -1) {
        let _number_arr = _number.split('.');
        _number = _number_arr[0];
        _number_decimals = _number_arr[1];
    }
    let _number_length = _number.length;
    let _string = '';
    let _num_parser = '';
    let _count = 0;
    for (let _p = (_number_length - 1); _p >= 0; _p--) {
        let _num_digit = _number.substr(_p, 1);
        _num_parser = _num_digit + _num_parser;
        if ((_num_parser.length == 3 || _p == 0) && !isNaN(parseFloat(_num_parser))) {
            _string = number_parser(_num_parser, _count) + _string;
            _num_parser = '';
            _count++;
        }
    }

    _string = _string.charAt(0).toUpperCase() + _string.substr(1).toLowerCase();
    return _string;
};

// https://rsdn.ru/forum/src/2899783.flat
function ua(number) {

    let mapNumbers = {
        0: [2, 1, "нуль"],
        1: [0, 2, "один", "одна"],
        2: [1, 2, "два", "дві"],
        3: [1, 1, "три"],
        4: [1, 1, "чотири"],
        5: [2, 1, "п'ять"],
        6: [2, 1, "шість"],
        7: [2, 1, "сім"],
        8: [2, 1, "вісім"],
        9: [2, 1, "дев'ять"],
        10: [2, 1, "десять"],
        11: [2, 1, "одинадцять"],
        12: [2, 1, "дванадцять"],
        13: [2, 1, "тринадцять"],
        14: [2, 1, "чотирнадцять"],
        15: [2, 1, "п'ятнадцять"],
        16: [2, 1, "шістнадцять"],
        17: [2, 1, "сімнадцять"],
        18: [2, 1, "вісімнадцять"],
        19: [2, 1, "дев'ятнадцять"],
        20: [2, 1, "двадцять"],
        30: [2, 1, "тридцять"],
        40: [2, 1, "сорок"],
        50: [2, 1, "п'ятдесят"],
        60: [2, 1, "шістдесят"],
        70: [2, 1, "сімдесят"],
        80: [2, 1, "вісімдесят"],
        90: [2, 1, "дев'яносто"],
        100: [2, 1, "сто"],
        200: [2, 1, "двісті"],
        300: [2, 1, "триста"],
        400: [2, 1, "чотириста"],
        500: [2, 1, "п'ятсот"],
        600: [2, 1, "шістсот"],
        700: [2, 1, "сімсот"],
        800: [2, 1, "вісімсот"],
        900: [2, 1, "дев'ятсот"]
    };

    let mapOrders = [
        {_Gender: false, _arrStates: ["гривня", "гривні", "гривень"], _bAddZeroWord: true},
        {_Gender: false, _arrStates: ["тисяча", "тисячі", "тисяч"]},
        {_Gender: true, _arrStates: ["мільйон", "мільйона", "мільйонів"]},
        {_Gender: true, _arrStates: ["мільярд", "мільярда", "мільярдів"]},
        {_Gender: true, _arrStates: ["триліон", "триліона", "триліонів"]}
    ];

    let objKop = {_Gender: false, _arrStates: ["копійка", "копійки", "копійок"]};

    function Value(dVal, bGender) {
        let xVal = mapNumbers[dVal];
        if (xVal[1] == 1) {
            return xVal[2];
        } else {
            return xVal[2 + (bGender ? 0 : 1)];
        }
    }

    function From0To999(fValue, oObjDesc, fnAddNum, fnAddDesc) {
        let nCurrState = 2;
        if (Math.floor(fValue / 100) > 0) {
            let fCurr = Math.floor(fValue / 100) * 100;
            fnAddNum(Value(fCurr, oObjDesc._Gender));
            nCurrState = mapNumbers[fCurr][0];
            fValue -= fCurr;
        }

        if (fValue < 20) {
            if (Math.floor(fValue) > 0 || (oObjDesc._bAddZeroWord)) {
                fnAddNum(Value(fValue, oObjDesc._Gender));
                nCurrState = mapNumbers[fValue][0];
            }
        } else {
            let fCurr = Math.floor(fValue / 10) * 10;
            fnAddNum(Value(fCurr, oObjDesc._Gender));
            nCurrState = mapNumbers[fCurr][0];
            fValue -= fCurr;

            if (Math.floor(fValue) > 0) {
                fnAddNum(Value(fValue, oObjDesc._Gender));
                nCurrState = mapNumbers[fValue][0];
            }
        }

        fnAddDesc(oObjDesc._arrStates[nCurrState]);
    }

    function FloatToSamplesInWordsUkr(fAmount) {
        let fInt = Math.floor(fAmount + 0.005);
        let fDec = Math.floor(((fAmount - fInt) * 100) + 0.5);

        let arrRet = [];
        let iOrder = 0;
        let arrSouthands = [];
        for (; fInt > 0.9999; fInt /= 1000) {
            arrSouthands.push(Math.floor(fInt % 1000));
        }
        if (arrSouthands.length == 0) {
            arrSouthands.push(0);
        }

        function PushToRes(strVal) {
            arrRet.push(strVal);
        }

        for (let iSouth = arrSouthands.length - 1; iSouth >= 0; --iSouth) {
            From0To999(arrSouthands[iSouth], mapOrders[iSouth], PushToRes, PushToRes);
        }

        if (arrRet.length > 0) {
            // Capitalize first letter
            arrRet[0] = arrRet[0].match(/^(.)/)[1].toLocaleUpperCase() + arrRet[0].match(/^.(.*)$/)[1];
        }

        arrRet.push((fDec < 10) ? ("0" + fDec) : ("" + fDec));
        From0To999(fDec, objKop, function () {
        }, PushToRes);

        return arrRet.join(" ");
    }

    return FloatToSamplesInWordsUkr(number);
}
