Question :
Out of Date Software :
1) Consider the file input.txt which contains all information about software installed on various servers in a data center:
Server1, Database, MySQL, 5.5
Server2, Database, MySQL, 5.1
Server3, OS, Ubuntu, 12.04
Server1, OS, Ubuntu, 12.04
Server2, OS, Ubuntu, 18.04
Server3, Language, Python, 2.6.3

2) This file indicates that Server1, has version 5.5 of MySQL installed, and Server2 has version 5.1 installed, and Server3 has version 12.04 of Ubuntu installed. For the purposes of this program, assume that all version numbers are of the form X.Y or X.Y.Z where X, Y, and Z are made up of only digits.

3) Write a Java program that reads this file (input.txt in the current directory), and prints to file output.txt  (in the current directory) a list of software package names for which an out-of-date version  (i.e. a version which is not the latest version) is installed on at least 2 different servers.
Thus, in this case, the output of your program should be:
Ubuntu
4) Because Ubuntu 12.04 is an out-of-date version (the latest version is 18.04), and it is installed on two servers (Server 3, and Server 1).
