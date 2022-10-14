# PromoFirstChapter
Задача по реализации списка 

Запись экрана: https://drive.google.com/file/d/1DODYiFozRQBtSPdmqKKl4OKG04Jqyw__/view?usp=sharing (На записи просто видео отработки тестов и запуск main, на случай, если у вас что-то упадет)

Реализация: 
* Собственная реализация списка называется ReznikovList. 
* Реализация - двусвязный список (подход с ForkJoinPool будет работать гораздо быстрее с массивом, чем со списком).
* Сортировка реализована через ForkJoinPool (многопоточная сортировка вставками, по-сути), вспомогательный алгоритм - рассческа
* Реализация находится в: /src/reznikov/list_implementation/implementations
* Интерфейс AuthorHolder: /src/reznikov/list_implementation/interfaces
* Класс-обёртка и Iterator'ы: /src/reznikov/list_implementation/models

Тесты:
* Разработан минимальный объем тестов, сравнивающих поведение стандартной и кастомной реализаций
* Разработан метод, позволяющий провести по возможности оптимальное временное сравнение стандартных и кастомной реализаций сортировки

Временной анализ:

Для того, чтобы проанализировать временные характеристики, был написан и запущен метод, сравнивающий временные характеристики моей и стандартных реализаций (LinkedList и ArrayList).
Готовый метод временной оценки - метод main в классе /src/reznikov/Main.java

Результаты сравнения:

```
size       ReznikovList  LinkedList  ArrayList
100        0            0            0            
200        0            0            0            
300        0            0            0            
400        0            0            0            
500        0            0            0            
600        1            0            0            
700        0            0            0            
800        1            0            0            
900        0            0            0            
size       ReznikovList  LinkedList  ArrayList
1000        1            0            0            
2000        1            1            1            
3000        3            1            1            
4000        4            2            4            
5000        4            2            2            
6000        5            1            1            
7000        6            2            3            
8000        9            3            3            
9000        6            3            3            
size       ReznikovList  LinkedList  ArrayList
10000        6            5            3            
20000        10            7            9            
30000        8            24            14            
40000        10            24            20            
size       ReznikovList  LinkedList  ArrayList
50000        15            31            27            
75000        22            32            30            
size       ReznikovList  LinkedList  ArrayList
100000        40            46            47            
125000        40            62            45            
150000        54            74            86            
175000        54            87            76            
size       ReznikovList  LinkedList  ArrayList
200000        61            162            90            
225000        73            99            99            
250000        78            136            116            
275000        190            161            150            
size       ReznikovList  LinkedList  ArrayList
300000        95            203            146            
325000        119            166            174            
350000        178            173            160            
375000        187            182            170            
size       ReznikovList  LinkedList  ArrayList
400000        158            200            223            
425000        177            229            202            
450000        174            202            207            
475000        258            270            225            
size       ReznikovList  LinkedList  ArrayList
500000        192            250            261            
525000        192            275            265            
550000        202            360            330            
575000        214            368            379            
size       ReznikovList  LinkedList  ArrayList
600000        264            477            440            
625000        278            524            423            
650000        291            510            453            
675000        286            525            484            
size       ReznikovList  LinkedList  ArrayList
700000        302            551            460            
725000        331            538            465            
750000        351            566            535            
775000        391            553            524            
size       ReznikovList  LinkedList  ArrayList
800000        377            603            570            
825000        369            589            528            
850000        362            674            562            
875000        394            624            591            
size       ReznikovList  LinkedList  ArrayList
900000        443            701            589            
925000        451            674            600            
950000        461            717            663            
975000        450            710            661            
size       ReznikovList  LinkedList  ArrayList
1000000        517            804            672
``` 
Как видно из представленных результатов, на малых наборах данных ReznikovList имеет худшее время сортировки, но при увеличении количества значений работает эффективнее стандартных реализаций. 
