# Парсер для JavaScript.ru

Парсер для адаптированного формата Markdown, который используется на Javascript.ru.

У него есть два режима работы:

1. Доверенный -- для статей, задач и другого основного материала. Возможны любые теги и т.п.
2. Безопасный -- для комментариев и другого user-generated content. Большинство тегов HTML можно использовать.

## Вставка кода ```js

Блок кода вставляется как в github:

<pre>
```js
alert(1);
```
</pre>

Или:
<pre>
```html
&lt;!DOCTYPE HTML&gt;
&lt;title&gt;Viva la HTML5!&lt;/title&gt;
```
</pre>

Поддерживаемые языки (список может быть расширен):

- html
- js
- css
- coffee
- php
- http
- java
- ruby
- scss
- sql

### Выполняемый код `//+ run` и другие настройки

Если хочется, чтобы посетитель мог запустить код -- добавьте первую строку `//+ run`:

<pre>
```js
//+ run
alert(1);
```
</pre>

Независимо от языка можно использовать любой стиль комментария: `//+ ... `, `/*+ ... */`, `#+ ...` или `<!--+ ... -->`,
главное чтобы он был *первой строкой* и в начале был *плюс и пробел*. Этот комментарий не попадёт в итоговый вывод.

Есть два языка, для которых это поддерживается:

1. `js` - в доверенном режиме через `eval`, в безопасном -- в `iframe` с другого домена.
2. `html` - в доверенном режиме показ будет в `iframe` с того же домена, в безопасном -- с другого домена.

Прочие настройки, возможные в этой же строке:

- `height=100` -- высота (в пикселях) для `iframe`, в котором будет выполняться пример. Обычно для HTML она вычисляется
  автоматически по содержимому.
- `src="my.js"` -- код будет взят из файла `my.js`
- `autorun` -- пример будет запущен автоматически по загрузке страницы.
- `refresh` -- каждый запуск JS-кода будет осуществлён в "чистом" окружении. Этот флаг актуален только для безопасного
  режима, т.к. обычно `iframe` с другого домена кешируется и используется многократно.
- `demo` - флаг актуален только для решений задач, он означает, что при нажатии на кнопку "Демо" в условии запустится
  этот код.

Пример ниже возьмёт код из файла `my.js` и запускает его автоматически:
<pre>
```js
//+ src="my.js" autorun
```
</pre>

Этот пример будет при запуске показан в `<iframe>` высотой `100px`:
<pre>
```html
&lt;!-- run height=100 -->
&lt;!DOCTYPE HTML>
&lt;html>
&lt;body>
  &lt;h1>Привет, мир!&lt;/h1>
&lt;/body>
&lt;/html>
```
</pre>

### Выделение в блоке кода `*!*`

Поддерживаются два выделения:

**Блочное выделение** -- несколько строк выделяются полностью.

Обозначается строками `*!*` в начале и `*/!*` в конце:
<pre>
```js
*!*
function important() {
  alert("Важный блок");
}
*/!*
```
</pre>

Также можно выделить отдельную строку (одну), поставив в конце `*!*`:

<pre>
```js
function important() {
  alert("Важная строка");*!*
}
```
</pre>

**Инлайн-выделение** выделяет фрагмент текста, например важное слово, для этого оно заключается в `*!*...*/!*`:
<pre>
```css
*!*h1*/!* {
  background: pink *!*important*/!*;
}
```
</pre>

В примере выше выделятся `h1` и `important`.

### Код в строке &#96;...&#96;

Для вставки кода в строку он оборачивается в обратные кавычки &#96;...&#96;.

Например:
<pre>
Функция `_.partial` делает то же, что и `bind`, но без привязки контекста `this`.
</pre>

Весь HTML внутри таких кавычек автоматически экранируется и оборачивается в `<code>`:
<pre>
Теги &#96;&lt;script&gt;&#96; и &#96;&lt;b&gt;&#96;
-- станет
Теги &lt;code&gt;&amp;lt;script&amp;gt;&lt;/code&gt; и &lt;code&gt;&amp;lt;b&amp;gt;&lt;/code&gt;
</pre>

Обычно это удобно, но если экранирование не нужно -- можно использовать HTML-тег `<code>...</code>` напрямую:

<pre>
Функция &lt;code>reduce&lt;em>Right&lt;/em>&lt;/code>
</pre>

После открывающей и перед закрывающей обратными кавычками &#96; не должно быть пробелов, такой текст не будет
отформатирован:
<pre>
От &#96; до &#96;
</pre>

Это позволяет избежать неверных интерпретаций в тексте. Если нужно вставить именно саму обратную кавычку, а она
воспринимается как код - используйте Unicode-entity: `&#96;`.

## Показ примеров в `<iframe>`

Во-первых, заметим, что любой js/html-код можно сделать запускаемым, добавив в начало `//+ run`.

При этом HTML будет при запуске показываться в `<iframe>` снизу. Можно даже добавить `autorun` для автозапуска при
загрузке страницы.

ББ-теги, описанные ниже, дают альтернативные способы показа примера.

Для того, чтобы они работали, пример должен быть *в отдельной директории без поддиректорий*, выложенной как plunk при
помощи утилиты https://github.com/iliakan/plunk. Кроме того, в примере должен быть файл `index.html`.

### `[iframe]` для показа примера в `<iframe>`

ББ-тег `[iframe]` позволяет показать пример в действии в `<iframe>`'е с минимальными "декорациями".

Например:
<pre>
[iframe:height=100 link play](/a/b)
</pre>

Покажет пример `cool-stuff/index.html`, без кода.

Параметры:

- `height=100` -- высота (если автовычисленная не подходит)
- `link` -- добавить в ифрейм ссылку для открытия в новом окне
- `play` -- добавить в ифрейм ссылку для открытия в песочнице
- `zip` -- добавить в ифрейм ссылку на скачивание архива с примером

Обычно чистый `[iframe src="..."]` используется для показа "как работает" пример без возможности залезть в код, например
в качестве демки для задачи.

### `[codetabs]` для показа файлов в табах

Если пример содержит несколько важных файлов -- его можно показать через `[codetabs]`.

Это то же самое, что `[iframe]`, но дополнительно над `<iframe>`'ом будет лента с табами файлов примера. Любой файл
можно выбрать и посмотреть.

Например:
<pre>
[codetabs](/cool-stuff)
</pre>

## Сравнение `[compare]`

Для показа списка достоинств/недостатков:

<pre>
```compare
+ WebSocket'ы дают минимальную задержку в передаче данных
+ WebSocket'ы позволяют непрерывно передавать данные в обе стороны
- Не поддерживаются в IE&lt;10
```
</pre>

У достоинств в начале должен стоять плюс `+`, у недостатков минус `-`, строки без `±` недопустимы.

## Ссылки `[...]()`

Ссылки можно задавать вместо `<a href="http://wikipedia.org">Википедия</a>` вот так:
<pre>
[Википедия](http://wikipedia.org)
</pre>

Для показа ссылки без особого заголовка:

<pre>
[http://wikipedia.org]()
-- станет
&lt;a href="http://wikipedia.org">http://wikipedia.org&lt;/a>
</pre>

Для ссылки на статью или задачу с сайта можно использовать только её абсолютный URL, заголовок подставится
автоматически, например:

<pre>
Читайте об этом в главе [](/events)
-- станет (из базы будет получен заголовок)
Читайте об этом в главе &lt;a href="/events">События&lt;/a> 
</pre>

Для того, чтобы сослаться на заголовок, у которого есть `[#anchor]`:

<pre>
[Оператор `instanceof`](#instanceof)
-- станет (если есть статья с заголовокм [#instanceof])
&lt;a href="/url-этой-статьи#instanceof">Оператор &lt;code>instanceof&lt;/code>&lt;/a>
</pre>

## Отмена форматирования `<pre>`, `[pre]`

Не применяется форматирование в HTML-комментариях `<!-- ... -->` и тегах:

- `<script>` `<style>` `<object>` `<embed>` `<video>` `<audio>` `<pre>`

Также форматирование не будет применяться, если обернуть секцию в `[pre]...[/pre]`:
<pre>
[pre]
В этом блоке.

Новые строки.

Не переносятся. И вообще, парсер не работает, просто обычный HTML.
[/pre]
</pre>

## Заголовки #Header и якоря [#anchor]

Заголовки начинаются с символа решётки `#`, сколько решёток -- такой и уровень.

На любой заголовок можно сделать общесайтовую ссылку, добавив к нему `[#anchor]`, где `anchor` -- имя
для `<a name="...">`, в который заголовок обёрнут.

Это имя также запоминается в базе и далее в любой другой статье или задаче можно просто использовать
ссылку `[читайте тут](#anchor)`
для отправки посетителя сразу на нужный заголовок.

## Жирный `**`, курсив `*`, перечёркивание `~`

Как в обычном Markdown:
<pre>
**жирный**
*курсив*
~перечёркивание~
</pre>

Выделение в середине слова не работает.

- Перед открывающим символом должен быть пробел, а после -- нет.
- После закрывающего символа должен быть побел или пунктуация, а перед -- нет пробела

Например:
<pre>
Мой *красивый* текст -- выделение сработает
*Мой красивый текст* -- выделение сработает
Мой*красивый*текст -- выделение не сработает (внутри слова)
a * b * c -- выделение не сработает (пробелы вокруг *)
</pre>

## Типографика

Автоматически заменяются:

- `(c)` `(r)` `(tm)` `(p)` `+-` `->` `<-` на символы `©` `®` `℗` `±` `→` `←`
- троеточие `...` на один юникодный символ-троеточие `…`
- одиночный дефис `-` на юникодный символ-аналог `&ndash;`, двойной дефис `--` на длинное тире `&mdash;`
- смайлы `:)` `:(` и ряд других - на картинки `<img src="файл для смайла">`
- кавычки `"..."` - на ёлочки `«...»`

Двойной (или более) перевод строки означает параграф `<p>`.

Не ставятся `<p>` между блочными тегами, например в таком тексте параграфа между `<div>` и `<table>` не будет:
<pre>
&lt;div&gt; ... &lt;/div&gt;

&lt;table&gt; ... &lt;/table&gt;
</pre>

## Сочетания клавиш `[key]`

Для красивого отображения сочетаний клавиш используется бб-тег `key:Ctrl+Shift+P`.

## Библиотеки `[libs]`

Если нужны одна или несколько библиотек -- перечислите их построчно в секции `[libs]`.

Например:
<pre>
```libs
http://code.jquery.com/jquery-latest.js
http://code.jquery.com/ui/1.10.4/jquery-ui.js
http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css
```
</pre>

Все они попадут в `<head>`, CSS будут до скриптов.

Можно перечислить не полные скрипты, а мнемо-имена, сейчас поддерживается только `d3`:

<pre>
[libs]
d3  
[/libs]
--- аналогично
[libs]
http://d3js.org/d3.v3.min.js
[/libs]
</pre>

## Скрипты и стили `[head]`

Скрипты и стили, которые хочется отправить в `<head>`, можно обернуть в `[head]`:

<pre>
[head]
$(function() {
  $('#slider-example').slider();
});
[/head]
</pre>

## Неподдерживаемый Markdown

- Списки (используйте `<ul>`, `<ol>` и `<dl>`).
- Таблицы (используйте `<table>`).
- Код при помощи отступов.
- Подчёркивание (лучше не использовать, достаточно `*` и `**`).
- Ряд других возможностей, используемых редко и имеющих HTML-аналоги.
