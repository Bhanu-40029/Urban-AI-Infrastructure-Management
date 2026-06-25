package DSA;

import Service.Asset;

public class AVLTree {

    private AVLNode root;

    // Height
    private int height(AVLNode node) {

        if(node == null)
            return 0;

        return node.height;
    }

    // Balance Factor
    private int getBalance(AVLNode node) {

        if(node == null)
            return 0;

        return height(node.left) - height(node.right);
    }

    // Right Rotation
    private AVLNode rightRotate(AVLNode y) {

        AVLNode x = y.left;
        AVLNode t2 = x.right;

        x.right = y;
        y.left = t2;

        y.height =
                Math.max(height(y.left),
                         height(y.right)) + 1;

        x.height =
                Math.max(height(x.left),
                         height(x.right)) + 1;

        return x;
    }

    // Left Rotation
    private AVLNode leftRotate(AVLNode x) {

        AVLNode y = x.right;
        AVLNode t2 = y.left;

        y.left = x;
        x.right = t2;

        x.height =
                Math.max(height(x.left),
                         height(x.right)) + 1;

        y.height =
                Math.max(height(y.left),
                         height(y.right)) + 1;

        return y;
    }

    // Public Insert
    public void insert(Asset asset) {

        root = insert(root, asset);
    }

    // Recursive Insert
    private AVLNode insert(AVLNode node,
                           Asset asset) {

        if(node == null)
            return new AVLNode(asset);

        if(asset.getAssetId()  < node.asset.getAssetId()) {

            node.left = insert(node.left, asset);
        }

        else if(asset.getAssetId()  > node.asset.getAssetId()) {

            node.right = insert(node.right, asset);
        }
        
        else {
            return node;
        }

        node.height =
                1 + Math.max(height(node.left),height(node.right));

        int balance =
                getBalance(node);

        // LL
        if(balance > 1 &&
           asset.getAssetId()
           < node.left.asset.getAssetId()) {

            return rightRotate(node);
        }

        // RR
        if(balance < -1 &&
           asset.getAssetId()
           > node.right.asset.getAssetId()) {

            return leftRotate(node);
        }

        // LR
        if(balance > 1 &&
           asset.getAssetId()
           > node.left.asset.getAssetId()) {

            node.left =
                    leftRotate(node.left);

            return rightRotate(node);
        }

        // RL
        if(balance < -1 &&
           asset.getAssetId()
           < node.right.asset.getAssetId()) {

            node.right =
                    rightRotate(node.right);

            return leftRotate(node);
        }

        return node;
    }

    // Search
    public Asset search(int assetId) {

        AVLNode current = root;

        while(current != null) {

            if(assetId ==
                    current.asset.getAssetId()) {

                return current.asset;
            }

            else if(assetId <
                    current.asset.getAssetId()) {

                current = current.left;
            }

            else {

                current = current.right;
            }
        }

        return null;
    }

    // Display All Assets
    public void inorder() {

        inorder(root);
    }

    private void inorder(AVLNode node) {

        if(node != null) {

            inorder(node.left);

            System.out.println(
                    node.asset);

            inorder(node.right);
        }
    }
}