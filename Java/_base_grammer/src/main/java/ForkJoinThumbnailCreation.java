import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;


/**
 * ForkJoinThumbnailCreation
 *
 * @Date 2024-10-15 15:33
 * @Author 杨海波
 **/
public class ForkJoinThumbnailCreation extends RecursiveAction {
    private static final int THRESHOLD = 50; // 任务阈值
    private final BufferedImage image;
    private final BufferedImage thumbnail;
    private final int startRow, endRow;
    private final int thumbnailWidth, thumbnailHeight;

    public ForkJoinThumbnailCreation(BufferedImage image, BufferedImage thumbnail,
                                     int startRow, int endRow,
                                     int thumbnailWidth, int thumbnailHeight) {
        this.image = image;
        this.thumbnail = thumbnail;
        this.startRow = startRow;
        this.endRow = endRow;
        this.thumbnailWidth = thumbnailWidth;
        this.thumbnailHeight = thumbnailHeight;
    }

    public static void main(String[] args) throws Exception {
        // 读取原始图像
        BufferedImage originalImage = ImageIO.read(new File("/Users/setsunayang/Documents/GitHub/base/Java/_base_grammer/src/main/resources/1.jpg"));
        int thumbnailWidth = 100; // 设置缩略图宽度
        int thumbnailHeight = 100; // 设置缩略图高度

        // 创建缩略图对象
        BufferedImage thumbnail = new BufferedImage(thumbnailWidth, thumbnailHeight, BufferedImage.TYPE_INT_RGB);

        // 使用 ForkJoinPool 创建缩略图
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinThumbnailCreation task = new ForkJoinThumbnailCreation(originalImage, thumbnail, 0, thumbnailHeight - 1, thumbnailWidth, thumbnailHeight);
        pool.invoke(task);

        // 保存生成的缩略图
        ImageIO.write(thumbnail, "jpg", new File("/Users/setsunayang/Documents/GitHub/base/Java/_base_grammer/src/main/resources/0.jpg"));
    }

    @Override
    protected void compute() {
        if (endRow - startRow <= THRESHOLD) {
            // 直接处理指定行的像素，生成缩略图
            for (int y = startRow; y <= endRow; y++) {
                for (int x = 0; x < thumbnailWidth; x++) {
                    // 计算原图对应的像素位置
                    int originalX = x * image.getWidth() / thumbnailWidth;
                    int originalY = y * image.getHeight() / thumbnailHeight;

                    // 获取原图像素并设置到缩略图
                    int rgb = image.getRGB(originalX, originalY);
                    thumbnail.setRGB(x, y, rgb);
                }
            }
        } else {
            // 拆分任务
            int middle = (startRow + endRow) / 2;
            ForkJoinThumbnailCreation topTask = new ForkJoinThumbnailCreation(image, thumbnail, startRow, middle, thumbnailWidth, thumbnailHeight);
            ForkJoinThumbnailCreation bottomTask = new ForkJoinThumbnailCreation(image, thumbnail, middle + 1, endRow, thumbnailWidth, thumbnailHeight);

            invokeAll(topTask, bottomTask);
        }
    }
}
