package hoods.com.newsy.features_presentations.home.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.InputChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import hoods.com.newsy.features_presentations.core.components.itemPadding
import hoods.com.newsy.utils.ArticleCategory

@Composable
fun DiscoverChips(
    selectedCategory: ArticleCategory,
    categories: List<ArticleCategory>,
    onCategoryChange: (ArticleCategory) -> Unit
) {
    LazyRow {
        items(categories) { category ->
            DiscoverChip(
                selected = category == selectedCategory,
                onClick = { onCategoryChange(category) },
                label = category.category
            )
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun DiscoverChip(
    selected: Boolean,
    onClick: () -> Unit,
    label: String
) {
    InputChip(
        selected = selected,
        onClick = onClick,
        label = { Text(text = label) },
        modifier = Modifier.padding(horizontal = itemPadding)
    )
}