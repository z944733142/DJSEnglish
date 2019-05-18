package com.djsenglish.controller;

import com.djsenglish.common.Const;
import com.djsenglish.common.ServerResponse;
import com.djsenglish.service.IWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping("add_sentence.do")
    @ResponseBody
    public ServerResponse addSentence(HttpServletRequest request, String sentence){
            Integer userId = (Integer) request.getAttribute(Const.ID);
            return iWordService.addSentence(userId, sentence);
    }

    @RequestMapping("get_sentences.do")
    @ResponseBody
    public ServerResponse getSentences(HttpServletRequest request){
        Integer userId = (Integer) request.getAttribute(Const.ID);
        return iWordService.getSentences(userId);
    }

    @RequestMapping("add_history.do")
    @ResponseBody
    public ServerResponse addHistory(HttpServletRequest request, String word) {
        Integer userId = (Integer) request.getAttribute(Const.ID);
        return iWordService.addHistory(userId, word);
    }

    @RequestMapping("delete_history.do")
    @ResponseBody
    public ServerResponse deleteHistory(HttpServletRequest request, @RequestParam(value = "word", required = false) String word, @RequestParam(value = "deleteAll", defaultValue = "false") boolean deleteAll) {
        Integer userId = (Integer) request.getAttribute(Const.ID);
        if (deleteAll) {
            return iWordService.deleteAll(userId);
        }
        return iWordService.deleteWord(userId, word);
    }

    @RequestMapping("history_list.do")
    @ResponseBody
    public ServerResponse historyList(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute(Const.ID);
        return iWordService.getList(userId);
    }
}
