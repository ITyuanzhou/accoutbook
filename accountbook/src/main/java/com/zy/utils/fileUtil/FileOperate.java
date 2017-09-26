package com.zy.utils.fileUtil;

import java.io.*;

/**
 * 文件操作类
 * Created by ZJZL_HP on 2017/8/30.
 */
public class FileOperate {

    /**
     * 复制文件或文件夹
     * @param srcPath 源文件或源文件夹的路径
     * @param destDir 目标文件所在的目录
     * @return
     */
    public static boolean copyGeneralFile(String srcPath, String destDir) {
        boolean flag = false;
        File file = new File(srcPath);
        if(!file.exists()) { // 源文件或源文件夹不存在
            return false;
        }

        if(file.isFile()) {    // 文件复制
            flag = copyFile(srcPath, destDir);
        }
        else if(file.isDirectory()) { // 文件夹复制
            flag = copyDirectory(srcPath, destDir);
        }

        return flag;
    }

    /**
     * 默认的复制文件方法，默认会覆盖目标文件夹下的同名文件
     * @param srcPath 源文件绝对路径
     * @param destDir 目标文件所在目录
     * @return boolean
     */
    public static boolean copyFile(String srcPath, String destDir) {
        return copyFile(srcPath, destDir, true/**overwriteExistFile*/); // 默认覆盖同名文件
    }

    /**
     * 默认的复制文件夹方法，默认会覆盖目标文件夹下的同名文件夹
     * @param srcPath    源文件夹路径
     * @param destDir    目标文件夹所在目录
     * @return boolean
     */
    public static boolean copyDirectory(String srcPath, String destDir) {
        return copyDirectory(srcPath, destDir, true/**overwriteExistDir*/);
    }


    /**
     * 复制文件到目标目录
     * @param srcPath 源文件绝对路径
     * @param destDir 目标文件所在目录
     * @param overwriteExistFile 是否覆盖目标目录下的同名文件
     * @return boolean
     */
    public static boolean copyFile(String srcPath, String destDir, boolean overwriteExistFile) {
        boolean flag = false;

        File srcFile = new File(srcPath);
        if (!srcFile.exists() || !srcFile.isFile()) { // 源文件不存在
            return false;
        }

        //获取待复制文件的文件名
        String fileName = srcFile.getName();
        String destPath = destDir + File.separator +fileName;
        File destFile = new File(destPath);
        if (destFile.getAbsolutePath().equals(srcFile.getAbsolutePath())) { // 源文件路径和目标文件路径重复
            return false;
        }
        if(destFile.exists() && !overwriteExistFile) {    // 目标目录下已有同名文件且不允许覆盖
            return false;
        }

        File destFileDir = new File(destDir);
        if(!destFileDir.exists() && !destFileDir.mkdirs()) { // 目录不存在并且创建目录失败直接返回
            return false;
        }try {
            FileInputStream fis = new FileInputStream(srcPath);
            FileOutputStream fos = new FileOutputStream(destFile);
            byte[] buf = new byte[1024];
            int c;
            while ((c = fis.read(buf)) != -1) {
                fos.write(buf, 0, c);
            }
            fos.flush();
            fis.close();
            fos.close();

            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return flag;
    }

    /**
     *
     * @param srcPath    源文件夹路径
     * @param destDir    目标文件夹所在目录
     * @return
     */
    public static boolean copyDirectory(String srcPath, String destDir, boolean overwriteExistDir) {
        if(destDir.contains(srcPath))
            return false;
        boolean flag = false;

        File srcFile = new File(srcPath);
        if (!srcFile.exists() || !srcFile.isDirectory()) { // 源文件夹不存在
            return false;
        }//获得待复制的文件夹的名字，比如待复制的文件夹为"E:\\dir\\"则获取的名字为"dir"
        String dirName = srcFile.getName();

        //目标文件夹的完整路径
        String destDirPath = destDir + File.separator + dirName + File.separator;
        File destDirFile = new File(destDirPath);
        if(destDirFile.getAbsolutePath().equals(srcFile.getAbsolutePath())) {
            return false;
        }
        if(destDirFile.exists() && destDirFile.isDirectory() && !overwriteExistDir) {    // 目标位置有一个同名文件夹且不允许覆盖同名文件夹，则直接返回false
            return false;
        }

        if(!destDirFile.exists() && !destDirFile.mkdirs()) {  // 如果目标目录不存在并且创建目录失败
            return false;
        }
        File[] fileList = srcFile.listFiles();    //获取源文件夹下的子文件和子文件夹
        if(fileList.length==0) {    // 如果源文件夹为空目录则直接设置flag为true，这一步非常隐蔽，debug了很久
            flag = true;
        }
        else {
            for(File temp: fileList) {
                if(temp.isFile()) {    // 文件
                    flag = copyFile(temp.getAbsolutePath(), destDirPath, overwriteExistDir);     // 递归复制时也继承覆盖属性
                }
                else if(temp.isDirectory()) {    // 文件夹
                    flag = copyDirectory(temp.getAbsolutePath(), destDirPath, overwriteExistDir);   // 递归复制时也继承覆盖属性
                }

                if(!flag) {
                    break;
                }
            }
        }

        return flag;
    }

    /**
     * 删除文件或文件夹
     * @param path
     *            待删除的文件的绝对路径
     * @return boolean
     */
    public static boolean deleteFile(String path) {
        boolean flag = false;

        File file = new File(path);
        if (!file.exists()) { // 文件不存在直接返回
            return flag;
        }
        flag = file.delete();

        return flag;
    }


    /**
     * 由上面方法延伸出剪切方法：复制+删除
     * @param  destDir 同上
     */
    public static boolean cutGeneralFile(String srcPath, String destDir) {
        boolean flag = false;
        if(copyGeneralFile(srcPath, destDir) && deleteFile(srcPath)) { // 复制和删除都成功
            flag = true;
        }

        return flag;
    }



}
