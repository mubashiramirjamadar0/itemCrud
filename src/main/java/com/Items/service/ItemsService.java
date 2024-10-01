package com.Items.service;


import com.Items.entity.Items;
import com.Items.repository.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
    public class ItemsService {

        @Autowired
        private ItemsRepository itemsRepository;

        public Items createItem(Items item) {
            return itemsRepository.save(item);
        }

        public List<Items> getAllItems() {
            return itemsRepository.findAll();
        }

        public Items getItemById(Long id) {
            return itemsRepository.findById(id).orElse(null); // or throw an exception
        }

        public Items updateItem(Long id, Items updatedItem) {
            // Add your update logic here
            return itemsRepository.save(updatedItem);
        }

        public void deleteItem(Long id) {
            itemsRepository.deleteById(id);
        }
    }
