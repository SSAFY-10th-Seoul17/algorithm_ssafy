s = input()
substrlist = set()
for i in range(len(s)):
    tmp = ""
    for j in range(i, len(s)):
        tmp += s[j]
        substrlist.add(tmp)
print(len(substrlist))