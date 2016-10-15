#!C:\Python27\python.exe -u
#!/usr/bin/env python

import pymysql.cursors
import cgi
from datetime import datetime

print "Content-type: text/html"

form = cgi.FieldStorage()

user = form.getvalue("user") #user id
campain = form.getvalue("character") #which character did the user choose
questionid = form.getvalue("question") #current question the user is on
startdate = form.getvalue("startdate") #when the user started playing to compare to today

date_object = datetime.now()
currentdate = date_object.strftime('%d-%m-%Y') #save curent date for comparison

currentgrade = form.getvalue("grade") #current grade

# Connect to the database
connection = pymysql.connect(host='localhost',
                             user='root',
                             password='17istheobjectivelybest',
                             db='choices',
                             charset='utf8mb4',
                             cursorclass=pymysql.cursors.DictCursor)

#now that we have the date from the client, save it to database
try:
    with connection.cursor() as cursor:
        # Create a new record
        sql = "INSERT INTO `users` (`id`, `campain`, `question`, `startdate`, `currentdate`, `grade`) VALUES (%s, %s, %s, %s, %s, %s)"
        cursor.execute(sql, (user, campain, questionid, startdate, currentdate, currentgrade) 

    # connection is not autocommit by default. So you must commit to save
    # your changes.
    connection.commit()

finally:
    connection.close()
