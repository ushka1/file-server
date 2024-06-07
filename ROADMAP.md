# Roadmap

## In Development

## To Do

- Add storage controller for client.
- Extract request headers to enum.

## Backlog

- Move from `File` class to `Path` class.
- Add other commands like `GET /list` to list all available files and IDs.
- Saving multiple files with same name. Create table with | ID (unique) | filename | fields. Files could be saved as `ID + ext`? When user fetches by filename and multiple files are found, response contains multiple IDS to choose?. `GET /id` always returns result, byt `GET /` can return message with available files. Or maybe `GET /` should return first result found?. Like "found more than one file with corresponding filename please specify it by id...". Not sure about this.
- Locking per filename in Storage, when some request holds reference to a file it locks access to this file for WRITE/DELETE operations. Threads that want to do such operations have to `.wait()` and later be `.wake()` or something. This should be own implementation, need to be designed. Consider asking on Stackoverflow for guidance.
- Add param validation. Consider adding `JAspect` for validation. E.g. `file-name`, `file-id`, `file-size`, etc.
- Write tests.
- Write integration tests.

## Done

- Detect disconnect <https://www.alpharithms.com/detecting-client-disconnections-java-sockets-091416/>.
- Rework request parsing.
- Add build and run scripts.
- Add logger.
- Add thread-safety to write and delete operations.
- Add SoTimeout.
- Same named files - do not create.
- Remove temp file after response.
- Resolve overwritting (no overwritting allowed).
- Add support for BufferedReader and BufferedWriter if needed.
- Improve logging.
- Handle case when user want to save file with name of file already present in directory.
