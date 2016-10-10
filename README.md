# ErlendW_inf5100

Advanced database systems assignment 



Mandetory assignment Erlend Westbye

Question 1

DSMS

This is a system that interacts with a data stream and not a “”static”” database, this type of system is used for querying results that will never be written to disk, and is useful for doing queries on live data. For example a sensor network that emits data. DSMS also often has installed queries that are used for polling for the qontinious stream of data, regardless of the arrival rate or sequence.

DBS

This is a more tradidional system for managing databases, where data is retrieved from and written to a static database. These type of systems are used for storing data, instead of just letting the data flow through the computer memory. 

Applications

There are a lot of use cases for dsms, one example is a Tsunami warning system where a multitude of sensors work toghether to report conditions that can be analysed by a dsms and create a warning message based on the data analysis.

Question 2

SELECT timestamp from StockTick WHERE close BETWEEN 17 AND 20

EVENT! {timestamp=Sat Jun 04 00:00:00 CEST 2011}
EVENT! {timestamp=Mon Jun 06 00:00:00 CEST 2011}
EVENT! {timestamp=Sun Jun 12 00:00:00 CEST 2011}
EVENT! {timestamp=Mon Jun 13 00:00:00 CEST 2011}
EVENT! {timestamp=Tue Jun 14 00:00:00 CEST 2011}
EVENT! {timestamp=Wed Jun 15 00:00:00 CEST 2011}
EVENT! {timestamp=Thu Jun 16 00:00:00 CEST 2011}
EVENT! {timestamp=Sun Jun 19 00:00:00 CEST 2011}
EVENT! {timestamp=Tue Jan 22 00:00:00 CET 2013}
EVENT! {timestamp=Wed Jan 23 00:00:00 CET 2013}
EVENT! {timestamp=Fri Jan 25 00:00:00 CET 2013}
EVENT! {timestamp=Sat Jan 26 00:00:00 CET 2013}
EVENT! {timestamp=Sun Jan 27 00:00:00 CET 2013}
EVENT! {timestamp=Mon Jan 28 00:00:00 CET 2013}
EVENT! {timestamp=Tue Jan 29 00:00:00 CET 2013}
EVENT! {timestamp=Wed Jan 30 00:00:00 CET 2013}
EVENT! {timestamp=Sat Feb 02 00:00:00 CET 2013}


Thoughts:

Here I’m thinking that it’s nice to have so many resources when it comes to generic sql statements. And that I was pleasently suprised that esper comes with so many of the ordenary sql capabilities. 

Question 3

A window is some sort of way to group your data, either by count, time, or some other method. The windows is used for grouping data that you are interested in looking at together. The window is another tool that can be used for making sense of the data that is available in your data stream.

The big difference is that you would need to identify a good window before the data arrives. In a traditional database you can query the data as many times as you want, so you can work out a good “window” over time. In a live system a good choice of window is essential to have the data make sense.

Aggregate functions in traditional db systems can be done on all the data, and you would be able to find the “true” result of aggregate such as avg, min max. But in a stream you are really only doing the aggregation on a small portion of the data (depending on the window you choose)


Question 4

SELECT MAX(weightedPrice) AS HighestPrice, MIN(weightedPrice) AS LowestPrice FROM StockTick.win:length_batch(100)

EVENT! {LowestPrice=0.06, HighestPrice=0.36}
EVENT! {LowestPrice=0.19, HighestPrice=1.06}
EVENT! {LowestPrice=0.67, HighestPrice=8.67}
EVENT! {LowestPrice=5.03, HighestPrice=29.58}
EVENT! {LowestPrice=2.14, HighestPrice=6.11}
EVENT! {LowestPrice=3.79, HighestPrice=7.05}
EVENT! {LowestPrice=4.72, HighestPrice=6.71}
EVENT! {LowestPrice=6.77, HighestPrice=13.26}
EVENT! {LowestPrice=10.31, HighestPrice=17.15}
EVENT! {LowestPrice=16.66, HighestPrice=214.67}
EVENT! {LowestPrice=70.26, HighestPrice=134.02}


Thoughts:

This query was interesting because it is the first that is using things that i do not recognize form regular sql. It is nice that there are built in functions for finding min and max, and the length_batch() was explained nicely in the documentation.

Question 5

SELECT MAX(weightedPrice) AS HighestPrice, MIN(weightedPrice) AS LowestPrice, MAX(weightedPrice)-MIN(weightedPrice) AS DiffMAXMinusMIN FROM StockTick.win:length_batch(100)

EVENT! {DiffMAXMinusMIN=0.3, LowestPrice=0.06, HighestPrice=0.36}
EVENT! {DiffMAXMinusMIN=0.8700000000000001, LowestPrice=0.19, HighestPrice=1.06}
EVENT! {DiffMAXMinusMIN=8.0, LowestPrice=0.67, HighestPrice=8.67}
EVENT! {DiffMAXMinusMIN=24.549999999999997, LowestPrice=5.03, HighestPrice=29.58}
EVENT! {DiffMAXMinusMIN=3.97, LowestPrice=2.14, HighestPrice=6.11}
EVENT! {DiffMAXMinusMIN=3.26, LowestPrice=3.79, HighestPrice=7.05}
EVENT! {DiffMAXMinusMIN=1.9900000000000002, LowestPrice=4.72, HighestPrice=6.71}
EVENT! {DiffMAXMinusMIN=6.49, LowestPrice=6.77, HighestPrice=13.26}
EVENT! {DiffMAXMinusMIN=6.839999999999998, LowestPrice=10.31, HighestPrice=17.15}
EVENT! {DiffMAXMinusMIN=198.01, LowestPrice=16.66, HighestPrice=214.67}
EVENT! {DiffMAXMinusMIN=63.760000000000005, LowestPrice=70.26, HighestPrice=134.02}


Thoughts:

This one is pretty similar to the query above, but it shows that it is possible to use the numeric operations that i would consider obvious. And it shows that it is very easy to show the values under a new name.

Question 6

SELECT AVG(weightedPrice) AS TheActualSum FROM StockTick.win:length_batch(100)
EVENT! {TheActualSum=0.0917}
EVENT! {TheActualSum=0.39289999999999864}
EVENT! {TheActualSum=2.3047000000000017}
EVENT! {TheActualSum=12.86079999999999}
EVENT! {TheActualSum=3.588699999999993}
EVENT! {TheActualSum=5.239999999999996}
EVENT! {TheActualSum=5.407999999999998}
EVENT! {TheActualSum=10.610999999999999}
EVENT! {TheActualSum=12.651000000000009}
EVENT! {TheActualSum=67.4092}
EVENT! {TheActualSum=105.50990000000007}
Thoughts:

Short and sweet. I like that there is a way to do this witout looping and adding to the sum one by one. +1 for aggregate functions.

Question 7

SELECT MIN(weightedPrice) AS minPrice, timestamp AS theDate FROM StockTick.win:length_batch(50) HAVING weightedPrice=MIN(weightedPrice)

EVENT! {theDate=Sun Aug 01 00:00:00 CEST 2010, minPrice=0.06}
EVENT! {theDate=Mon Sep 20 00:00:00 CEST 2010, minPrice=0.06}
EVENT! {theDate=Fri Dec 10 00:00:00 CET 2010, minPrice=0.19}
EVENT! {theDate=Wed Dec 29 00:00:00 CET 2010, minPrice=0.29}
EVENT! {theDate=Tue Apr 05 00:00:00 CEST 2011, minPrice=0.67}
EVENT! {theDate=Sat Apr 09 00:00:00 CEST 2011, minPrice=0.74}
EVENT! {theDate=Sun May 29 00:00:00 CEST 2011, minPrice=8.3}
EVENT! {theDate=Sat Sep 10 00:00:00 CEST 2011, minPrice=5.03}
EVENT! {theDate=Wed Oct 19 00:00:00 CEST 2011, minPrice=2.28}
EVENT! {theDate=Fri Nov 18 00:00:00 CET 2011, minPrice=2.14}
EVENT! {theDate=Thu Dec 22 00:00:00 CET 2011, minPrice=3.79}
EVENT! {theDate=Thu Feb 16 00:00:00 CET 2012, minPrice=4.19}
EVENT! {theDate=Sun Apr 08 00:00:00 CEST 2012, minPrice=4.72}
EVENT! {theDate=Tue May 22 00:00:00 CEST 2012, minPrice=5.08}
EVENT! {theDate=Sat Jul 07 00:00:00 CEST 2012, minPrice=6.77}
EVENT! {theDate=Sat Sep 01 00:00:00 CEST 2012, minPrice=10.0}
EVENT! {theDate=Fri Oct 26 00:00:00 CEST 2012, minPrice=10.31}
EVENT! {theDate=Tue Dec 04 00:00:00 CET 2012, minPrice=13.03}
EVENT! {theDate=Fri Jan 25 00:00:00 CET 2013, minPrice=16.66}
EVENT! {theDate=Sat Mar 16 00:00:00 CET 2013, minPrice=46.86}
EVENT! {theDate=Fri May 03 00:00:00 CEST 2013, minPrice=91.3}
EVENT! {theDate=Sat Jul 06 00:00:00 CEST 2013, minPrice=70.26}


Thoughts:

Nice query, pretty sweet. Having is a cool keyword. I also like that you are able to do multiple batch lengths and still get a sensible output. Renaming is also cool

Question 8

Patterns are a way to get matching events from a data set/stream. These are very useful for only returning the data that you are interested in. Patterns look a lot like how you would get data from an object in oop and therefore will feel very familiar to programers.

every -> is a pattern that itterates over all instanses before the arrow, and returns the events that matches the condition behind the arrow. This syntax is very useful for itterating over the data set, and finding lines/objects that match your requirements.

every A -> B

Every event A followed by event B

A -> every B

Fires for every event B following event A

every A -> every B

Fires for evere event A followed by every event B

every (A-> B)
Fires for an event A followed by event B. When it finds a mach it starts looking for the next maching situation



Question 9

select a.timestamp, b.timestamp from pattern[every a=StockTick -> b=StockTick(b.weightedPrice >= a.weightedPrice*100 AND b.volume/a.volume>=0.4)]

EVENT! {a.timestamp=Sun Aug 01 00:00:00 CEST 2010, b.timestamp=Fri May 13 00:00:00 CEST 2011}
EVENT! {a.timestamp=Tue Oct 12 00:00:00 CEST 2010, b.timestamp=Wed Jun 01 00:00:00 CEST 2011}
EVENT! {a.timestamp=Sun Oct 10 00:00:00 CEST 2010, b.timestamp=Thu Jun 02 00:00:00 CEST 2011}
EVENT! {a.timestamp=Fri Oct 08 00:00:00 CEST 2010, b.timestamp=Fri Jun 03 00:00:00 CEST 2011}
EVENT! {a.timestamp=Mon Oct 25 00:00:00 CEST 2010, b.timestamp=Sat Jun 04 00:00:00 CEST 2011}
EVENT! {a.timestamp=Wed Oct 27 00:00:00 CEST 2010, b.timestamp=Mon Jun 06 00:00:00 CEST 2011}
EVENT! {a.timestamp=Thu Oct 28 00:00:00 CEST 2010, b.timestamp=Tue Jun 07 00:00:00 CEST 2011}
EVENT! {a.timestamp=Sat Oct 09 00:00:00 CEST 2010, b.timestamp=Wed Jun 08 00:00:00 CEST 2011}
EVENT! {a.timestamp=Sat Nov 06 00:00:00 CET 2010, b.timestamp=Thu Jun 09 00:00:00 CEST 2011}
EVENT! {a.timestamp=Thu Dec 30 00:00:00 CET 2010, b.timestamp=Fri Feb 22 00:00:00 CET 2013}
EVENT! {a.timestamp=Fri Jan 07 00:00:00 CET 2011, b.timestamp=Tue Feb 26 00:00:00 CET 2013}
EVENT! {a.timestamp=Mon Nov 08 00:00:00 CET 2010, b.timestamp=Thu Feb 28 00:00:00 CET 2013}
EVENT! {a.timestamp=Wed Jan 12 00:00:00 CET 2011, b.timestamp=Fri Mar 01 00:00:00 CET 2013}
EVENT! {a.timestamp=Mon Jan 17 00:00:00 CET 2011, b.timestamp=Mon Mar 04 00:00:00 CET 2013}
EVENT! {a.timestamp=Sun Nov 07 00:00:00 CET 2010, b.timestamp=Tue Mar 05 00:00:00 CET 2013}
EVENT! {a.timestamp=Fri Jan 21 00:00:00 CET 2011, b.timestamp=Wed Mar 06 00:00:00 CET 2013}
EVENT! {a.timestamp=Sun Jan 30 00:00:00 CET 2011, b.timestamp=Sun Mar 10 00:00:00 CET 2013}
EVENT! {a.timestamp=Mon Jan 31 00:00:00 CET 2011, b.timestamp=Tue Mar 19 00:00:00 CET 2013}
EVENT! {a.timestamp=Mon Apr 04 00:00:00 CEST 2011, b.timestamp=Thu Mar 21 00:00:00 CET 2013}
EVENT! {a.timestamp=Tue Feb 01 00:00:00 CET 2011, b.timestamp=Mon Mar 25 00:00:00 CET 2013}
EVENT! {a.timestamp=Fri Mar 18 00:00:00 CET 2011, b.timestamp=Tue Mar 26 00:00:00 CET 2013}
EVENT! {a.timestamp=Fri Feb 04 00:00:00 CET 2011, b.timestamp=Wed Mar 27 00:00:00 CET 2013}
EVENT! {a.timestamp=Sat Feb 05 00:00:00 CET 2011, b.timestamp=Thu Mar 28 00:00:00 CET 2013}
EVENT! {a.timestamp=Tue Feb 22 00:00:00 CET 2011, b.timestamp=Fri Mar 29 00:00:00 CET 2013}
EVENT! {a.timestamp=Tue Feb 08 00:00:00 CET 2011, b.timestamp=Sat Mar 30 00:00:00 CET 2013}
EVENT! {a.timestamp=Wed Mar 02 00:00:00 CET 2011, b.timestamp=Sun Mar 31 00:00:00 CET 2013}
EVENT! {a.timestamp=Wed Feb 09 00:00:00 CET 2011, b.timestamp=Mon Apr 01 00:00:00 CEST 2013}
EVENT! {a.timestamp=Sat Feb 12 00:00:00 CET 2011, b.timestamp=Tue Apr 02 00:00:00 CEST 2013}
EVENT! {a.timestamp=Sun Apr 17 00:00:00 CEST 2011, b.timestamp=Wed Apr 03 00:00:00 CEST 2013}
EVENT! {a.timestamp=Fri Apr 22 00:00:00 CEST 2011, b.timestamp=Thu Apr 04 00:00:00 CEST 2013}
EVENT! {a.timestamp=Sat Apr 23 00:00:00 CEST 2011, b.timestamp=Mon Apr 08 00:00:00 CEST 2013}
EVENT! {a.timestamp=Wed Apr 27 00:00:00 CEST 2011, b.timestamp=Tue Apr 09 00:00:00 CEST 2013}


Thoughts:

I struggeled for a while with this query. And it took a while to understand the syntax of patterns. After i understood the syntax it started to make more sense. And i found that this way of queriyng is wery close to how im used to using loops and object attributes. At this point the power of esper was really starting to show

Question 10

Centralized dbs: A system where the data is contained on one site in a non distributed fashion. 

Distributed dbs: A system that stores data across multiple nodes. This may be distributed across multiple machines in a building or all over the world. The only condition is that the data is distributed across multiple machines/nodes.

One reason why you might want a Distributed system is if you have multiple locations that need accsess to the same data. This also ensures that the data is consistant through the different locations. 

Advantages:

Improves performance.

Ease of accsess; The end user can accsess all the distributed data from one location.

More secure: Harder to bring the system down, as the data is distributed and these type of systems often have a high degree of fault tollerance built in.

Cheap: Processing and storing can be split among many “cheap” disks and cpus, this makes these type of systems cheap in certein situations.

It is very scaleable.

Disadvantages:

Network: You would need a very fast and well configured network to avoid bottlenecks in your system. This is a factor you don’t need to worry about when you hav a centralized system.

Uptime: You are very depentent on the connectivity of your infrastructure. If a cable is cut for some reason, the machine that the system is tryng to reach might work just fine. But you would not be able to retrieve data from that node.


Question 11

SELECT initial.timestamp, initial.weightedPrice, buy.timestamp, sell.timestamp FROM pattern[every initial=StockTick -> buy=StockTick( buy.weightedPrice < initial.weightedPrice AND buy.volume/initial.volume >= 0.7) OR sell=StockTick(sell.weightedPrice*3 >= initial.weightedPrice AND sell.volume/initial.volume>=0.7)]

Thoughts:

This one was a bit hard to figure out, but when all of the conditions were met, the output made a lot of sense. In retrospect i probably should have added AS to rename some of the results. But when the variables are printed it is still pretty easy to see where everything belongs

Question 12

SELECT en.timestamp, to.timestamp, tre.timestamp FROM pattern[every en=StockTick -> to=StockTick(to.weightedPrice > en.weightedPrice  AND to.close > en.close) -> tre=StockTick(tre.weightedPrice > to.weightedPrice AND tre.close > to.close)]

Thoughts:

This one showed that the -> just makes a lot of sense, i like that you can stack it like this. 

Question 13

Per the documentation of esper; pattern guards are “where” conditios that control the lifecycle of the subexpressions. These are for example timer:within, timer:withinmax and while-expression.

In a simulated enviroment like the one we have in this task, the time window would not return the data that you are actually interested in. If we take a look at the datetimes of the btc-prices (a list of stock prices, price per day) we can se that these events are emitted once per day. But if we where to use a timer, we would get the events for 3 years in a fraction of a second.

An alternative is using EPL date-time function to control time. You are still able to compare dates, add time etc. This can be used in a query to find the matching time/date. 

Question 14

select startStock.timestamp, startStock.weightedPrice, stopStock.timestamp, stopStock.weightedPrice FROM pattern[every startStock=StockTick -> stopStock=StockTick(stopStock.weightedPrice <= startStock.weightedPrice/3 AND stopStock.timestamp.between(startStock.timestamp, startStock.timestamp.plus(15 days)))]   

Thoughts:

This query nicely illustrates what i stated in the task above. This query also made the date functunality very clear. Here i struggeled for a while with understanding that you could just add “days, hours, minutes etc” and i tried to calculate 15 days from milliseconds wich was pretty dumb.

Question 15

A heterogeneous database system is a system for collecting multiple databases automatically. Even if the databases are different (heterogeneous). The goal is to present the user with a single interface for queriyng data across multiple databses.

A HDBS needs to integrate heterogeneous data stores and present a single interface that lets you query across all of the data stores in the system. 

The integration layer is a software layer that is responsible for handeling the integration of the different heterogeneous data stores. This is the layer that the user can query, and maps the query so that it returns the correct data. So because of this it needs of all the data models, and schemas  of the other data stores.

As with any integration in programming you are going to run in to issues regarding compatability. It is also much harder to keep track of all the transactions in the hdbs, scince it needs to keep track of all of the transaction of every single system that it intefgrates with.


Question 16

The hardest part of the this assignment was understanding patterns. This was some work, but when a costudent showed me the way to do it. All of the other assignments that revolved around this became a lot easier to do.

Another part that was difficoult was to write enough info on each of the questions. This was challanging becaus i felt that i was not able to add enough fluff info on each of the questions.

All of the problems were because of moi.

