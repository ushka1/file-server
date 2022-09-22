# File Server

## Intro

Simple file server with console client. Server is able to handle multiple connections and store all types of files.

## Requirements

- Java version: `18`

## Setup

Run `build.sh` script in order to build project.

Alternatively you can open project in VSCode with installed [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack), then you can omit build part because extension will do it for you. You can start services using above scripts, using `launch.json` tasks in **Run and Debug** tab or using VSCode **Run/Debug Java** button.

## Data

To store data services will create following directories:

- `data/server/storage` - server location for storing files.
- `data/server/temp` - temporary server location for newly uploaded files.
- `data/server/config` - location for storing persistent data of server (e.g. identifiers (IDs) of files).
- `data/client` - from that location client service sends/downloads files to/from server.

These locations can be customized under `src/server/config/Constants.java` and `src/client/config/Constants.java`.

## Usage

1. Start server using `start_server.sh` script.
1. Start client using `start_client.sh` script.
1. Add some files under newly created `data/client` directory.
1. Now you can interact with server using client.

## Request Structure

```
GET <path>
<param>=<value>
<param>=<value>
...
<param>=<value>

```

```
DELETE <path>
<param>=<value>
<param>=<value>
...
<param>=<value>

```

```
POST <path>
<param>=<value>
<param>=<value>
...
<param>=<value>

<bytes>
```
