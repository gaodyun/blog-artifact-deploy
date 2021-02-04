#!/usr/bin/env bash

#GNU C的头文件<sysexits.h>中对于状态码的定义
#define EX_OK      0    /* successful termination */
#define EX__BASE    64   /* base value for error messages */
#define EX_USAGE    64   /* command line usage error */
#define EX_DATAERR   65   /* data format error */
#define EX_NOINPUT   66   /* cannot open input */
#define EX_NOUSER    67   /* addressee unknown */
#define EX_NOHOST    68   /* host name unknown */
#define EX_UNAVAILABLE 69   /* service unavailable */
#define EX_SOFTWARE   70   /* internal software error */
#define EX_OSERR    71   /* system error (e.g., can't fork) */
#define EX_OSFILE    72   /* critical OS file missing */
#define EX_CANTCREAT  73   /* can't create (user) output file */
#define EX_IOERR    74   /* input/output error */
#define EX_TEMPFAIL   75   /* temp failure; user is invited to retry */
#define EX_PROTOCOL   76   /* remote error in protocol */
#define EX_NOPERM    77   /* permission denied */
#define EX_CONFIG    78   /* configuration error */
#define EX__MAX        78   /* maximum listed value */

if test $# -ne 3
then
  echo "参数个数异常: 当前个数[$#], 需要个数[3]"
  exit 64
fi

# $1 博客名称
readonly blogName="$1"
# $2 nginx路径
readonly serverPath="$2"
# $3 artifact路径
readonly artifactPath="$3"

echo "[${blogName} Blog] 开始更新"
echo "清空目录 [${serverPath}] ..."
# rm -rf /srv/http/blog/*
rm -rf "${serverPath}"
echo "重建目录 [${serverPath}] ..."
mkdir -p "${serverPath}"
echo "解压 artifact.zip 到目录 [${serverPath}] ..."
#unzip -qq "${artifactPath}" -d "${serverPath}"
# 此处-O UTF-8的选项是为了解决中文目录和中文文件名解压以后乱码的问题
# 部分环境的unzip可能没有-O选项，需要想办法装一个支持-O选项的unzip，或者通过其它方式解压
unzip -O UTF-8 -qq "${artifactPath}" -d "${serverPath}"
echo "删除 artifact.zip ..."
rm -f "${artifactPath}"
echo "[${blogName} Blog] 完成更新"

exit 0
