Парсер логов

Лог файл имеет следующий формат:
ip username date event status

Информация о событии должна предоставляться по запросу.
Общий формат запроса с параметром:
get field1 for field2 = "value1"
Где: 
field1 - одно из полей: ip, user, date, event или status;
field2 - одно из полей: ip, user, date, event или status;
value1 - параметр по которому нужно выполнить поиск, значение поля field2.

Примеры запросов с параметром:
1) get ip for user = "Vasya"
2) get user for event = "DONE_TASK"
3) get event for date = "03.01.2014 03:45:23"