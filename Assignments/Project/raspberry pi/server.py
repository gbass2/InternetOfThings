# import socket programming library
import socket
import os
import sys
import requests

from simulations.simulations import *

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

        # updating the database
        if 'updateGarage' in data_list:
            update_garage(data_list[1],data_list[3],data_list[2])
        if 'updateHouse' in data_list:
            update_house(data_list[1],data_list[2])
        if 'updateLight' in data_list:
            update_light(data_list[1],data_list[2],data_list[3],data_list[4],data_list[5])
        if 'updateFanMode' in data_list:
            update_fan(data_list[1],data_list[2],data_list[3])
        if 'updateMode' in data_list:
            update_mode(data_list[1],data_list[2],data_list[3])
        if 'updateControlTemp' in data_list:
            update_temperature(data_list[1],data_list[2],data_list[3])


    # connection closed
    c.close()

def wait_for_network():
    while True:
        try:
            r = requests.head(url='http://google.com/',timeout=3)
            return
        except requests.ConnectionError as e:
            pass

def Main():
    hostname = socket.gethostname()
    host = socket.gethostbyname(hostname)
    port = 12312

    wait_for_network()

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
