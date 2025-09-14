package dsj.smtc;

public class Main {

    public static void main(String[] args) {
        while (true) {
            String info = SmtcLoader.getSmtcInfo(); // Libs
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

