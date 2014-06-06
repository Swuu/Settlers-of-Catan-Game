public class Item
{
	public int priceClay;
	public int priceLumber;
	public int priceOre;
	public int priceSheep;
	public int priceWheat;
	
	public Item(int pClay, int pLumber, int pOre, int pSheep, int pWheat)
	{
		priceClay = pClay;
		priceLumber = pLumber;
		priceOre = pOre;
		priceSheep = pSheep;
		priceWheat = pWheat;
	}
    
    public void changePrice(int pClay, int pLumber, int pOre, int pSheep, int pWheat)
    {
        priceClay = pClay;
		priceLumber = pLumber;
		priceOre = pOre;
		priceSheep = pSheep;
		priceWheat = pWheat;
    }
}
