package com.wan7451.binderpool;


interface ISecurityCenter {
    String encrypt(String content);
    String decrypt(String password);
}
