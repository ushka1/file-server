## IMPORTANT NOTE

Headers and query params (and simple body) merged into params.

```
GET <path>
<param>: <value>
<param>: <value>
<param>: <value>

```

```
DELETE <path>
<param>: <value>
<param>: <value>
<param>: <value>

```

```
POST <path>
<param>: <value>
<param>: <value>
<param>: <value>

<bytes>
```

## In Development

## To Do

- When you getFile() and File instance is associated with res, content of file is not immediately read, but waits for response to send(), so if someone would remove that file, then we have problem. Also there is problem with reading/writing by others while this file will be read by Response.
- Error handling (StorageController)
- Write tests.

## Backlog

- Replace `writeUTF` with something different.
- Param validation.
- Add `JAspect` and add validators.
- Add Dockerfile and Docker-Compose.
- Rework build and run scripts.
- Make notes about byte[] -> String -> byte[] and String -> byte[] -> String.
- Further investgate HTTP request (with multipart especially).
- Improve streams https://docs.oracle.com/javase/tutorial/essential/io/datastreams.html
- Add TextField implementation (with `:qa` ending) if user didn't passed filename.

## Done

- Detect disconnect https://www.alpharithms.com/detecting-client-disconnections-java-sockets-091416/.
- Rework request parsing.
- Add build and run scripts.
- Add logger.
- Add thread-safety to write and delete operations.
- Add SoTimeout.
- Same named files - do not create.
- Remove temp file after response.
- Resolve overwritting (no overwritting allowed).
