/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ldn.service;

import com.ldn.pojo.Comment;
import java.util.List;

/**
 *
 * @author three
 */
public interface CommentService {
    public List<Comment> getListCommentsByProductId(int productId);
}
