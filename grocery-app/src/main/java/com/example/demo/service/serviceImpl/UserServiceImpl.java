package com.example.demo.service.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.entity.Booking;
import com.example.demo.entity.GroceryItem;
import com.example.demo.exception.ItemNotFoundException;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.GroceryItemRepository;
import com.example.demo.request.ItemsDto;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private GroceryItemRepository groceryItemRepository;

	@Autowired
	private BookingRepository bookingRepository;

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public Iterable<GroceryItem> viewAllGroceryItems() {

		try {
			// Implement logic to view all grocery items
			return groceryItemRepository.findAll();
		} catch (Exception e) {
			logger.error("Error viewing all grocery items", e);
			throw new RuntimeException("Error viewing all grocery items", e);
		}

	}

	@Override
	public Booking bookGroceryItems(@RequestBody ItemsDto itemsDto) {

		List<Integer> itemIds = itemsDto.getItems();
		try {
			List<GroceryItem> items = itemIds.stream().map(groceryItemRepository::findById).filter(Optional::isPresent)
					.map(Optional::get).collect(Collectors.toList());

			if (items.contains(null)) {
				throw new ItemNotFoundException("One or more items not found");
			}

			Booking booking = new Booking();
			booking.setItems(items);

			bookingRepository.save(booking);

			logger.info("Successfully booked grocery items with IDs: {}", itemIds);
			return booking;
		} catch (Exception e) {
			logger.error("Error booking grocery items", e);
			throw new RuntimeException("Error booking grocery items", e);
		}
	}

}
