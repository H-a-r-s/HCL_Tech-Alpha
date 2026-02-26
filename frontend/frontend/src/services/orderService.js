// Order API aligned with backend (via Gateway 8080):
// POST /api/orders -> { userId, items: [{ productId, quantity }] }
// GET /api/orders/user/{userId} -> Order[] with { id, userId, totalAmount, status, createdAt, items }

import api from './api';

export function placeOrder({ userId, items }) {
  return api.post('/api/orders', {
    userId,
    items: items.map(({ productId, quantity }) => ({ productId, quantity }))
  });
}

export function getOrdersByUser(userId) {
  return api.get(`/api/orders/user/${userId}`);
}

