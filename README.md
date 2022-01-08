# ChatAndTabManager
Этот плагин поможет вам в лёгком создании чата и таба для вашего сервера Minecraft.
- [Начало работы с плагином](#Установка)
- [Конфиг плагина и его параметры](#Конфигурация)
## Установка
Плагин разрабатывается в интегрированной среде разработки, а название ей ꟷ [IntelliJ IDEA](https://jetbrains.com/idea/).
Для начала работы с плагином вам необходимы две библиотеки:
* [Spigot-1.8.8](https://getbukkit.org/download/spigot)
* [PermissionsEx-1.23.4](https://github.com/PEXPlugins/PermissionsEx/releases/tag/STABLE-1.23.4)

Инструкции по IDE:
* [Как добавить библиотеку в свой проект](https://www.jetbrains.com/help/idea/library.html#define-a-project-library)
* [Конфигурация артефакта](https://www.jetbrains.com/help/idea/working-with-artifacts.html#configure_artifact)
## Конфигурация
Изначальный вид конфигурационного файла:
```yaml
playerTabFormat: "{player_prefix}{player_nickname}"
joinMessage: "{player_prefix}{player_nickname} &r&6зашёл в лобби!"
joinNotifications: true
chat: "{player_prefix}{player_nickname}{player_suffix}: {player_message}"
globalChat: "[G] {player_prefix}{player_nickname}{player_suffix}: {player_message}"
localChat: "[L] {player_prefix}{player_nickname}{player_suffix}: {player_message}"
ifNoOneHeardTheMessage: "&cВаше сообщение никто не услышал"
globalPrefix: "!"
localRadius: 100
isGlobalEnabled: false
isChatClickable: true
clickableType: 0
clickableValue: "/profile {player_nickname}"
isChatHover: true
hoverMessage: "{player_prefix}{player_nickname}\n§7Уровень: §60"
```

Основные переменные в конфиге:
* `{player_prefix}` - префикс игрока, получаемый через PermissionsEx
* `{player_suffix}` - суффикс игрока, получаемый через PermissionsEx
* `{player_nickname}` - ник игрока
* `{player_message}` - сообщение, которое написал игрок

Теперь-же пройдёмся по строкам:
* `playerTabFormat` - строка, формат отображения игрока в табе, устанавливается при заходе на сервер
* `joinMessage` - строка, формат сообщения о заходе игрока на сервер
* `joinNotifications` - логическое выражение. **Примечание: если значение равно `false`, то при заходе будет вывод стандартного сообщения о входе**
* `chat` - строка, формат сообщения при отправке в чат. **Примечание: если значение `isGlobalEnabled` равно `true`, то чат будет форматироваться по значению этого параметра**
* `globalChat` - строка, формат сообщения при отправке в глобальный чат. **Примечание: работает, если значение `isGlobalEnabled` равно `true`, и перед сообщением стоит установленный вами `globalPrefix`**
* `localChat` - строка, формат сообщения при отправке в локальный чат. **Примечание: работает, если значение `isGlobalEnabled` равно `true`, перед сообщением не стоит установленный вами `globalPrefix`, и если в установленном вами радиусе `localRadius` есть хоть два игрока**
* `ifNoOneHeardTheMessage` - строка. **Примечание: отправляется игроку, если в установленном вами радиусе `localRadius` нет ни одного игрока**
* `globalPrefix` - строка, префикс перед сообщением для отправки в глобальный чат
* `localRadius` - целое число
* `isGlobalEnabled` - логическое выражение. **Примечание: если параметр равен `false`, будет использовано форматирование `chat`**
* `isChatClickable` - логическое выражение. **Примечание: если параметр равен `true`, при нажатии на сообщение будет выполнена команда/ссылка**
* `clickableType` - целое число, имеет 3 типа:
  * `RUN_COMMAND` - выполняет строку как команду из `clickableValue`
  * `OPEN_FILE` - выполняет переход по ссылке как файл из `clickableValue`
  * `OPEN_URL` - выполняет переход по ссылке из `clickableValue`
* `isChatHover` - логическое выражение. **Примечание: если параметр равен `true`, при наведении на сообщение будет всплывающее окно с вашим текстом, взятый из `hoverMessage`**