## In Development

## To Do

- [CLIENT] Make separate functions on every type of request with own response handling. Like in a normal web-app bruh. Current flow is kinda shitty you know.
- Splitting with "=" can cause problems when <value> contains "=".
- Handle case when user want to save file with name of file already present in directory.
- Add support for BufferedReader and BufferedWriter if needed.
- Think about upgrading logging.

## Backlog

- Add other commands like GET /list to list all available IDS with filenames.
- Saving multiple files with same name. Create table with | ID (unique) | filename | fields. Files should be saved as ID + extension/".bin" (?). When user fetches by filename and multiple files are found, response contains multiple IDS to choose (?). GET_BY_ID always returns result, byt GET_BY_FILENAME can return message with available files. Or maybe GET_BY_FILENAME should return first result found (?). Not sure about this. OR maybe return error with that message. Like "found more than one file with corresponding filename please specify it by id...".
- Locking per filename in Storage, when some request holds reference to a file it locks access to this file for WRITE/DELETE operations. Threads that want to do such operations have to .wait() and later be .wake() or something. This should be own implementation, need to be designed. Also you can ask on Stackoverflow.
- Add param validation. Consider adding `JAspect` for validation.
- Write tests.
- Write integration tests.
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
