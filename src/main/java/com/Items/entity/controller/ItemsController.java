package com.Items.entity.controller;

import com.Items.entity.Items;  // Make sure this is your entity class
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/items")
public class ItemsController {

    private List<Items> items = new ArrayList<>();
    private long currentId = 1;
    //localhost:8080/api/items
    // Create - POST

    @PostMapping("/api/items")
    public ResponseEntity<List<Items>> createItems(@RequestBody List<Items> items) {
        // Process the list of items
        return ResponseEntity.ok(items);
    }

    //localhost:8080/api/items/{id}
    // Read - GET all
    @GetMapping
    public List<Items> getAllItems() {
        return items;
    }
    //localhost:8080/api/items/1
    // Read - GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<Items> getItemById(@PathVariable Long id) {
        Optional<Items> item = items.stream().filter(i -> i.getId().equals(id)).findFirst();
        return item.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    //localhost:8080/api/items/{id}
    // Update - PUT
    @PutMapping("/{id}")
    public ResponseEntity<Items> updateItem(@PathVariable Long id, @RequestBody Items updatedItem) {
        for (Items item : items) {
            if (item.getId().equals(id)) {
                item.setName(updatedItem.getName());
                return ResponseEntity.ok(item);
            }
        }
        return ResponseEntity.notFound().build();
    }
    //localhost:8080/api/items/1
    // Delete - DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        boolean removed = items.removeIf(item -> item.getId().equals(id));
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
