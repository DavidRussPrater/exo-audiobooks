# Design System: Stitch (Google) - Exo Audiobooks

This project follows the **Stitch** design language, Google’s framework for information-rich experiences. It focuses on modularity, clear information hierarchy, and a fluid "river" of content.

## 1. Core Principles
- **Informative & Modular:** Content is broken down into "Stitch Units" (Cards/Modules) that can be easily rearranged.
- **Adaptive:** The UI seamlessly transitions between mobile, tablet, and foldable form factors using the Material Adaptive library.
- **Expressive:** Use of high-quality imagery (book covers) and subtle brand-colored containers.

## 2. Foundation
### Color Palette (Material 3)
The app uses a **Dynamic Color** system based on the user's wallpaper or the current audiobook cover art.
- **Primary:** Brand Purple/Blue (reflecting "Exo" space themes).
- **Surface:** Tonal surfaces to create depth without using heavy borders.
- **Scrims:** Soft gradients behind text on imagery to ensure legibility.

### Typography
- **Headlines:** `Google Sans` (Display) for a modern, friendly feel.
- **Body:** `Roboto` or `Inter` for high legibility in long descriptions.
- **Labels:** All-caps or title-case metadata labels for consistent scanning.

## 3. Stitch Units (Components)
### The "Hero" Unit
- Used for the active playback screen and featured audiobooks.
- Features large-scale cover art with a "glassmorphic" or tonal background bleed.

### Content Cards
- Standardized corner radius (`28dp` or `16dp` based on container).
- Metadata "stashed" below the title for a clean primary scanning line.

### Control Chips
- Used for categories (e.g., "Fiction", "Sci-Fi").
- Rounded buttons with tonal fills to distinguish from primary actions.

## 4. Layout & Navigation
- **The River:** A vertical scroll of information units that feel "stitched" together.
- **Navigation Suite:** 
  - **Mobile:** Bottom Navigation Bar.
  - **Tablet/Foldable:** Navigation Rail or Permanent Drawer.
- **Edge-to-Edge:** All screens draw behind the status and navigation bars for maximum immersion.

## 5. Iconography
- **Style:** Material Symbols (Outlined) for a light, clean look.
- **Optical Size:** `24px` for navigation, `20px` for inline metadata.

## 6. Motion
- **Shared Z-Axis:** For moving between list and detail views.
- **Container Transform:** When tapping a book cover, the cover expands to become the background of the Player view.
