# import socket programming library
import socket
import os
import sys
import videosave

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

        # forward data to the pi
        if 'updateGarage' in data_list or 'updateHouse' in data_list:
            client("192.168.1.198",12312,data)
        else:
            if data_list[2] == '1':
                # call floor 1
                client("192.168.1.198",12312,data)
            if data_list[2] == '2':
                # call floor 2
                print('pi2')
                client("192.168.2.32",12313,data)


    # connection closed
    c.close()

# Client socket to forward the information
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



def main():
    # Start the videosave for the cameras
    # Starting video save for camera 1
    vid_thread = threading.Thread(target=videosave.save,daemon=True, args=(r'/opt/lampp/htdocs/49ersense/videos',r'http://192.168.1.198:8000/save_feed'))
    rm_thread = threading.Thread(target=videosave.remove_video,daemon=True, args=(r'/opt/lampp/htdocs/49ersense/videos',))

    vid_thread.start()
    rm_thread.start()

    # Creating the server socket
    host = socket.gethostbyname(socket.gethostname())
    print(host)

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
    main()
