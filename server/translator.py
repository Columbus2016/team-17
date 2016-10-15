#!C:\Python27\python.exe -u
#!/usr/bin/env python

import pymysql.cursors
import cgi

print "Content-type: text/html"

form = cgi.FieldStorage()
print form.getvalue("user", "data")

# Connect to the database
connection = pymysql.connect(host='localhost',
                             user='root',
                             password='17istheobjectivelybest',
                             db='choices',
                             charset='utf8mb4',
                             cursorclass=pymysql.cursors.DictCursor)

try:
    with connection.cursor() as cursor:
        # Create a new record
        sql = "INSERT INTO `users` (`id`, `good`) VALUES (%s, %s)"
        cursor.execute(sql, ('123', '666'))

    # connection is not autocommit by default. So you must commit to save
    # your changes.
    connection.commit()

finally:
    connection.close()
