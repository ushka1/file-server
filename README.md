# File Server

## Intro

Simple file server with client. Server is able to store all types of files.

## Requirements

- Java version: `18`

## Setup

Run `build.sh` script in order to build project. Next you can start services using `start_client.sh` and `start_server.sh` scripts.

Alternatively you can open project in VSCode with installed [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack), then you can omit build part because extension will do it for you. You can start services using above scripts, using `launch.json` tasks in **Run and Debug** tab or using VSCode **Run/Debug Java** button.

## Request Structure

```
GET <path>
<param>=<value>
<param>=<value>
<param>=<value>

```

```
DELETE <path>
<param>=<value>
<param>=<value>
<param>=<value>

```

```
POST <path>
<param>=<value>
<param>=<value>
<param>=<value>

<bytes>
```
