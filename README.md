# science-conference
Учебный проект
* Для запуска нужно создать пустую БД в **Postgres** и задать доступ к ней в `resources/application.properties` в первых строчках, начинающихся с `spring.datasource.*`
* create-insert скрипт первой версии БД, нарушающей три нормальных формы лежит в `resources/db/versions/db_v1.sql`
* create-insert скрипт второй версии БД, исправлющий эти нарушения лежит рядом, в `resources/db/versions/db_v2.sql`
* Нетривиальные запросы сохранены в `resources/db/queries`
