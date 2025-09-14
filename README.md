# SMTC JNI

这是一个基于 **Java JNI** 的 Windows 专用库，内部封装了 `smtc.dll`，可以在引入 JAR 后自动加载 DLL，无需额外配置路径。

## 项目结构

> `resources` 文件夹已配置为资源目录，打包 JAR 时会自动包含 DLL。

---
## 主要功能
- 获取当前播放的媒体标题
- 获取播放进度
- 获取总时长
- 获取封面图 (Base64)
---

## 使用方法

### 1. 引入 JAR

将构建好的 JAR 文件添加到你的 Java 项目的依赖库中.

### 2. 调用示例

```java
public class Main {

    public static void main(String[] args) {
        while (true) {
            String info = SmtcLoader.getSmtcInfo(); // Lib
            String[] parts = info.split("\\|", -1);

            if (parts.length >= 4 && !"No media".equals(parts[0])) {
                // 歌曲标题
                String title = parts[0];

                // 当前播放进度
                long pos = Long.parseLong(parts[1]);

                // 总时长
                long dur = Long.parseLong(parts[2]);

                // Base64
                String base64 = parts[3];
                int b64Len = base64.isEmpty() ? 0 : base64.length();

                System.out.printf(
                        "\rNow Playing: %s | Progress: %02d:%02d / %02d:%02d | Base64:%d   ",
                        title,                // 歌曲名
                        pos / 60, pos % 60,   // 已播放时间
                        dur / 60, dur % 60,   // 总时长
                        b64Len                // Base64 字符串长度
                );
            } else {
                System.out.print("\rNo media playing | Base64:0                              ");
            }
        }
    }
}
