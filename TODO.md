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

- Write tests.
- LOCK per filename in StorageImpl, when some request holds reference to a file it is locked and thread is waiting (.wait(), .interrupt() etc) - OWN IMPLEMENTATION D:

## Backlog

- Ask stackoverflow about file get/delete/add and synchronization
- Replace `writeUTF` with something different.
- Param validation.
- Add `JAspect` and add validators.
- Make notes about byte[] -> String -> byte[] and String -> byte[] -> String.

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
