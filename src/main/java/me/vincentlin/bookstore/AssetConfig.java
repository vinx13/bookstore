package me.vincentlin.bookstore;

import me.vincentlin.bookstore.AssetProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;


import java.util.stream.Collectors;


/**
 * Created by Vincent on 2017/3/30.
 */
@ControllerAdvice
public class AssetConfig {
    private AssetProperties.Asset admin, store;

    @Autowired
    public AssetConfig(AssetProperties assetProperties) {
        admin = assetProperties.getAdmin();
        store = assetProperties.getStore();
        addAssetPrefix(admin);
        addAssetPrefix(store);
    }

    private void addAssetPrefix(AssetProperties.Asset asset) {
        asset.setStyles(asset.getStyles().stream().map(s -> "/styles/" + s).collect(Collectors.toList()));
        asset.setScripts(asset.getScripts().stream().map(s -> "/scripts/" + s).collect(Collectors.toList()));
    }

    @ModelAttribute("adminAsset")
    public AssetProperties.Asset getAdminAsset() {
        return admin;
    }

    @ModelAttribute("storeAsset")
    public AssetProperties.Asset getStoreAsset() {
        return store;
    }


}
