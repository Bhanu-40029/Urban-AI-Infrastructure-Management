package DSA;

import Service.Asset;

public class AVLNode {

    Asset asset;
    AVLNode left;
    AVLNode right;
    int height;

    public AVLNode(Asset asset) {

        this.asset = asset;
        this.height = 1;
    }
}