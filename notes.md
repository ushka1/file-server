# Notes

## Intro

Client communicates with server using request messages that are loosely based on [HTTP messages](https://developer.mozilla.org/en-US/docs/Web/HTTP/Messages) (of course highly simplified).

## Request structure

```
<method> <path>
<param>=<value>
<param>=<value>
...
<param>=<value>

<body?>
```

- single start-line - that consist of a `method` and a `path`, separated with a single space;
- multiple params - where every parameter is written in single line. Line consists of a `param` which is param name and a `value` which is param value, separated with `=` sign;
- empty line - for marking end of parameters,
- body - which is optional and written in bytes. It is used for file transfer and will be read by server only if `POST` request was done.

Everything except body is written using ` writeUTF(String str)` method. Body is written in 4kb parts using `write(byte[] b, int off, int len)`. The same is the case with reading data.

## Accepted methods

- `GET`
- `POST`
- `DELETE`

## Essential params

- `message` - message from the server to be displayed on client;
- `file-name` - name of the file to be saved on/fetched from the server;
- `file-size` - size of the file to be saved on/fetched from the server;
- `file-id` - id of the file to be fetched from the server;

## Example requests

```
GET /
file-name=keyboard_cat.jpg

```

```
DELETE /id
file-id=12345

```

```
POST /
file-name=keyboard_cat.jpg
file-size=2137

<bytes>
```

## Responses

Server response is very similar to client request, with one difference. Start line instead of having `method` and `path`, consists of a `statusCode` indicating status of response.

## Example responses

```
200
message=File 'keyboard_cat.jpg' was sent.
file-name=keyboard_cat.jpg
file-size=2137

```

```
404
message=File 'keyboard_cat.jpg' not found.

```
