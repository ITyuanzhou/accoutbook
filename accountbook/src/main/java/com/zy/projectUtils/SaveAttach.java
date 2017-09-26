package com.zy.projectUtils;

import com.zy.model.TbAttachEntity;
import com.zy.utils.fileUtil.FileOperate;

import javax.servlet.http.HttpServletRequest;

/**
 * 保存上传文件类
 * Created by ZJZL_HP on 2017/8/30.
 */
public class SaveAttach {
    /**
     * 保存操作里面的新建
     * @param request
     * @param newAttach 新福建信息
     */
    public static void Save(HttpServletRequest request,TbAttachEntity newAttach){
        if(newAttach != null)  //将文件从临时文件夹调转过来
            SaveAttach.CutTempToSave(request,newAttach);
    }

    /**
     * 保存操作里面的更新
     * @param request
     * @param oldAttach   原来附件信息
     * @param newAttach   新附件信息
     */
    public static void Update(HttpServletRequest request,TbAttachEntity oldAttach,TbAttachEntity newAttach){
        if(newAttach!=null &&(oldAttach == null || (oldAttach!=null && !newAttach.getAttachFileUuid().equals(oldAttach.getAttachFileUuid()))))
            SaveAttach.CutTempToSave(request,newAttach);

        if( oldAttach!= null &&(newAttach == null || (newAttach!=null && !oldAttach.getAttachFileUuid().equals(newAttach.getAttachFileUuid()))))
            SaveAttach.DeleteOldAttach(request,oldAttach);
    }

    /**
     * 剪切临时文件夹的文件到指定文件夹中
     * @param request
     * @param attach
     */
    public static void CutTempToSave(HttpServletRequest request, TbAttachEntity attach){
        String sc = request.getSession().getServletContext().getRealPath("");
        String baseSc = sc.substring(0,sc.substring(0,sc.lastIndexOf("\\")).lastIndexOf("\\"));

        String fileName = attach.getAttachFileUuid()+ ProjectConstant.File_Bound_Symbol + attach.getAttachFileName();
        String cutFilePath = baseSc + ProjectConstant.TempFile_Relative_Path +"/" +fileName;
        String pasteDicPath = baseSc + attach.getAttachFilePosition();
        FileOperate.cutGeneralFile(cutFilePath,pasteDicPath);
    }

    /**
     * 删除老文件
     * @param request
     * @param attach
     */
    public static void DeleteOldAttach(HttpServletRequest request,TbAttachEntity attach){
        String sc = request.getSession().getServletContext().getRealPath("");
        String baseSc = sc.substring(0,sc.substring(0,sc.lastIndexOf("\\")).lastIndexOf("\\"));

        String fileName = attach.getAttachFileUuid()+ ProjectConstant.File_Bound_Symbol + attach.getAttachFileName();
        String deleteDicPath = baseSc + attach.getAttachFilePosition();
        String deleteFiePath = deleteDicPath + "/" + fileName;

        FileOperate.deleteFile(deleteFiePath);
    }
}
