# import socket programming library
import socket
import os
import sys

# import thread module
from _thread import *
import threading

print_lock = threading.Lock()

# thread function
def threaded(c):
    while True:
        # data received from client
        data = c.recv(1024)
        if not data:
            print('Bye')

            # lock released on exit
            print_lock.release()
            break

        data = data.decode('UTF-8').strip('\n')
        print(data)
        data_list = data.split(',')
        print(data_list)

        # forward data to the pi
        if 'updateGarage' or 'updateHouse' in data_list:
            client("192.168.2.31",12312,data)
        else:
            if data_list[2] == '1':
                print('pi1')
                # call floor 1
                client("192.168.2.31",12312,data)
            if data_list[2] == '2':
                # call floor 2
                client("192.168.2.32",12313,data)
                

    # connection closed
    c.close()

def client(host_ip, port, data):
    try:
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        print ("Socket successfully created")
    except socket.error as err:
        print ("socket creation failed with error %s" %(err))
 
    # connecting to the pi
    s.connect((host_ip, port))
    
    # sending the data to the pi
    s.send(data.encode('UTF-8'))
    
    s.close()
    
    

def Main():
    host = "192.168.2.30"

    # reverse a port on your computer
    # in our case it is 12345 but it
    # can be anything
    port = 10000
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.bind((host, port))
    print("socket binded to port", port)

    # put the socket into listening mode
    s.listen(5)
    print("socket is listening")

    while True:

        # establish connection with client
        c, addr = s.accept()

        # lock acquired by client
        print_lock.acquire()
        print('Connected to :', addr[0], ':', addr[1])

        # Start a new thread and return its identifier
        start_new_thread(threaded, (c,))
    s.close()


if __name__ == '__main__':
    Main()
