// Product API aligned with backend (via Gateway 8080):
// GET /api/products, /api/products/{id}, /api/brands, /api/categories
// ProductResponse: { id, brandId, brandName, categoryId, categoryName, name, description, price, isActive }

import api from './api';

export function getProducts(filters = {}) {
  return api.get('/api/products', { params: { brandId: filters.brandId, categoryId: filters.categoryId } });
}

export function getProduct(id) {
  return api.get(`/api/products/${id}`);
}

export function getBrands() {
  return api.get('/api/brands');
}

export function getCategories() {
  return api.get('/api/categories');
}


