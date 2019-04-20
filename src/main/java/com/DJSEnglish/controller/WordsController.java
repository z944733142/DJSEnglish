package com.DJSEnglish.controller;

import com.DJSEnglish.common.Const;
import com.DJSEnglish.common.ServerResponse;
import com.DJSEnglish.pojo.User;
import com.DJSEnglish.service.IWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequestMapping("/words/")
@Controller
public class WordsController {

    @Autowired
    private IWordService iWordService;


    @RequestMapping("get_words.do")
    @ResponseBody
    public ServerResponse getWords(HttpServletRequest request) {
        return iWordService.getWord();
    }

    //    @RequestMapping("add_sentence.do")
//    @ResponseBody
//    public ServerResponse addSentence(HttpServletRequest request){
//
//
//    }
    @RequestMapping("add_history.do")
    @ResponseBody
    public ServerResponse addHistory(HttpServletRequest request, String word) {
        Integer id = (Integer) request.getAttribute(Const.ID);
        return iWordService.addHistory(id, word);
    }

    @RequestMapping("delete_history.do")
    @ResponseBody
    public ServerResponse deleteHistory(HttpServletRequest request, @RequestParam(value = "word", required = false) String word, @RequestParam(value = "deleteAll", defaultValue = "false") boolean deleteAll) {
        Integer id = (Integer) request.getAttribute(Const.ID);
        if (deleteAll) {
            return iWordService.deleteAll(id);
        }
        return iWordService.deleteWord(id, word);
    }

    @RequestMapping("history_list.do")
    @ResponseBody
    public ServerResponse historyList(HttpServletRequest request) {
        Integer id = (Integer) request.getAttribute(Const.ID);
        return iWordService.getList(id);
    }
}
