## Intro

Articles related to this project are in Chrome under `bookmarks/programming/projects/file_server`.

## Build

1. Javac can only compile `.java` files and give `.class` output. There is no way to tell javac to parse/include `.properties` or any other files. You have to copy them manually.

## Exceptions

1. `java.net.SocketException: Broken pipe` errors (usually) occur when the client socket is closed before the request can be completed. These errors are harmless. <br> Example situation: User hits the update button and closes the browser while the application is calculating.

## Console command deconstruction

[Web Link](https://stackoverflow.com/questions/36495669/difference-between-terms-option-argument-and-parameter)

Having example command:
`ls -la /tmp /var/temp`

```
args = ["ls", "-la", "/tmp", "/var"]
options = ["-l", "-a"]
parameters = ["/tmp", "/var/temp"]
```

Parsing can happen no matter of order of passed options/parameters. All below can be correct (depending on implementation and needs).

```
ls -la /tmp /var/temp
ls /tmp /var/temp -la
ls -l /tmp -a /var/temp
```

## Communication and Sending Files

1. You can write many times to stream, and read many times from it. And this reading can be sequential (I mean you can use `readUTF()` many times and got correct output every time) because of below.
1. `readUTF()` (and other similar) can work because `writeUTF()` writes 2 bytes of length before actual UTF string, so reader know what is going on because it treats this 2 bytes like a header. Same applies to `readInt()`, `readLong()` and so on (?).
1. However you should stick to client-server meaning 1 request is 1 response. Remember that you implement that pattern on your own, it is not like built-in TCP sockets.
1. For req and res base on HTTP protocol implementation (articles in Chrome).
1. Do not close TCP socket while you are using it. But remember to set SoTimeout so socket will not hand on `read()` for too long `socket.setSoTimeout(timeout)`.
1. Transfer files in chunks (article in Chrome).
1. You can try to clean trash after request using `input.skip(input.available())`.
1. When you receive file from user, store it in temporary location and give it unique id. Then you can for example copy it to its proper destination. After completing request remove temp-file. And add method to remove every temp-file related to this client when client disconnects. You can also create user specifc directory in `temp` directory with unique id, and then remove this directory on disconnect.

## Other

1. [Use of new `String[0]` in `toArray(new String[0])`](https://stackoverflow.com/questions/18136437/whats-the-use-of-new-string0-in-toarraynew-string0)
