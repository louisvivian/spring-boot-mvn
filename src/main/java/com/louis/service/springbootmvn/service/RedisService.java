package com.louis.service.springbootmvn.service;

import com.alibaba.fastjson.JSON;
import com.louis.service.springbootmvn.component.RedisRepository;
import com.louis.service.springbootmvn.entity.MerchantStoreClassItemExtendEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * RedisService
 *
 * @author wangxing
 * @date 2019-5-5
 */
@Service
public class RedisService {

    @Autowired
    private RedisRepository redisRepository;

    public List<MerchantStoreClassItemExtendEntity> get(Integer merchantId, Integer storeId, Integer classId, Integer itemId, Integer valueId) {
//        String key = String.format("ITEM:%s:%s:%s:%s:%s", merchantId, storeId, classId, itemId, valueId);
//        String value = redisRepository.get(key);

        String key = String.format("ITEM:%s:%s:%s", merchantId, storeId,classId);
        List<MerchantStoreClassItemExtendEntity> keyList = new ArrayList<>(10);
        Set<String> keys = redisRepository.getKeys(key + "*");

        // Set to List
//        keyList = new ArrayList<>(keys);

        int iAdd = 0;
        for (String key1 : keys) {

            String value1 = redisRepository.get(key1);
            MerchantStoreClassItemExtendEntity merchantStoreClassItemExtendEntity = JSON.parseObject(value1, MerchantStoreClassItemExtendEntity.class);
            if (merchantStoreClassItemExtendEntity.getSortOrder() < 9999){
//                keyList.add(merchantStoreClassItemExtendEntity);
//                break;
                iAdd++;
            }


        }

        System.out.println(iAdd);
        return keyList;
    }


}
