// Cart API aligned with backend (via Gateway 8080):
// POST /cart/add, GET /cart/{userId}, DELETE /cart/remove/{id}, DELETE /cart/clear/{userId}

import api from './api';

export function addToCart({ userId, productId, quantity }) {
  return api.post('/cart/add', { userId, productId, quantity });
}

export function getCart(userId) {
  return api.get(`/cart/${userId}`);
}

export function removeCartItem(id) {
  return api.delete(`/cart/remove/${id}`);
}

export function clearCart(userId) {
  return api.delete(`/cart/clear/${userId}`);
}
