# File Server

## Description

Local file server with a console client that supports multiple connections and stores all file types.

## Setup

### Requirements

- `java 18`
- `junit-4.13.2.jar` (testing)
- `hamcrest-core-1.3.jar` (testing)

Jar files should be kept in the /lib directory.

### Build

To build the project, run the `build.sh` script.

Alternatively, you can open the project in VSCode with the [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack) installed. This extension handles the build process automatically. You can start the services using the provided scripts, the `launch.json` tasks in the **Run and Debug tab**, or the **Run/Debug Java** button.

### Usage

1. Start the server using the `start_server.sh` script.
1. Start the client using the `start_client.sh` script.
1. Add files to the newly created `data/client` directory.
1. You can now interact with the server using the client.

### Data storage

To store data, the services will create the following directories:

- `data/server/storage` - Server location for storing files.
- `data/server/temp` - Temporary server location for newly uploaded files.
- `data/server/config` - Location for storing persistent server data (e.g. file identifiers).
- `data/client` - Location from which the client service sends and downloads files to/from the server.

These locations can be customized in `src/server/config/Constants.java` and s`rc/client/config/Constants.java`.

## Docs

The client communicates with the server using request messages loosely based on [HTTP messages](https://developer.mozilla.org/en-US/docs/Web/HTTP/Messages).

### Request structure

```txt
<method> <path>
<param>=<value>
<param>=<value>
...
<param>=<value>

<body?>
```

- **Single start-line** - That consist of a `method` and a `path`, separated with a single space.
- **Multiple params** - Where every parameter is written in single line. Line consists of a `param` which is param name and a `value` which is param value, separated with `=` sign.
- **Empty line** - For marking end of parameters.
- **Body** - Which is optional and written in bytes. It is used for file transfer and will be read by server only if `POST` request was done.

Everything except body is written using `writeUTF(String str)` method. Body is written in 4kb parts using `write(byte[] b, int off, int len)`. The same is the case with reading data.

#### Accepted methods

- `GET`
- `POST`
- `DELETE`

#### Essential params

- `message` - Message from the server to be displayed on client.
- `file-name` - Name of the file to be saved on/fetched from the server.
- `file-size` - Size of the file to be saved on/fetched from the server.
- `file-id` - Id of the file to be fetched from the server.

#### Example requests

```txt
GET /
file-name=keyboard_cat.jpg

```

```txt
DELETE /id
file-id=12345

```

```txt
POST /
file-name=keyboard_cat.jpg
file-size=2137

<bytes>
```

### Response structure

The server response is similar to the client request, with one key difference: the start line consists of a `statusCode` indicating the status of the response, instead of a `method` and `path`.

#### Example responses

```txt
200
message=File 'keyboard_cat.jpg' was sent.
file-name=keyboard_cat.jpg
file-size=2137

```

```txt
404
message=File 'keyboard_cat.jpg' not found.

```

## Roadmap

Roadmap can be found under [ROADMAP.md](./ROADMAP.md).
