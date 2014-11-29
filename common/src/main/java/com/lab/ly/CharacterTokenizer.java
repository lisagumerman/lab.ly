package com.lab.ly;

/**
 * User: haswell
 * Date: 11/23/14
 * Time: 5:19 PM
 */
public interface CharacterTokenizer {

    char peek();
    char advance();

    void push(char ch);


}
