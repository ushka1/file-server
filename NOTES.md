### Console command deconstruction

[link](<(https://stackoverflow.com/questions/36495669/difference-between-terms-option-argument-and-parameter)>)

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

### Use of new `String[0]` in `toArray(new String[0])`

[link](https://stackoverflow.com/questions/18136437/whats-the-use-of-new-string0-in-toarraynew-string0)
