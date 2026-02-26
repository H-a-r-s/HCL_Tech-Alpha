// Inventory API aligned with backend:
// GET /api/inventory/{productId} -> InventoryResponse { productId, stockQty, updatedAt }

import api from './api';

export function getStock(productId) {
  return api.get(`/api/inventory/${productId}`);
}

