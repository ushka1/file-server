## In Development

## To Do

- Splitting with "=" can be problematic when we send "=" in mesage!
- Multiple file saving, do table with | ID (unique) | filename | and files should be saved as ID + eventually bin?. Then when user fetches by filename and multiple files are found, we response with multiple files found and list available ids.

## Backlog

- LOCK per filename in StorageImpl, when some request holds reference to a file it is locked and thread is waiting (.wait(), .interrupt() etc) - OWN IMPLEMENTATION D:
- On client save files with unique id to avoid overwritting.
- Buffered Reader/Writer usage.
- Ask stackoverflow about file get/delete/add and synchronization.
- Replace `writeUTF` with something different.
- Param validation.
- Add `JAspect` and add validators.
- Make notes about byte[] -> String -> byte[] and String -> byte[] -> String.
- Write tests.

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

```

```
