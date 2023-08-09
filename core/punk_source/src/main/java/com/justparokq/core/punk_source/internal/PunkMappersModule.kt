package com.justparokq.core.punk_source.internal

import com.justparokq.core.common.mapper.Mapper
import com.justparokq.core.database.model.BeerListItem
import com.justparokq.core.model.beer_sorts.*
import com.justparokq.core.punk_source.api.mapper.*
import com.justparokq.core.punk_source.api.model.web.BeerResponse
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PunkMappersModule {

    @Binds
    abstract fun provideBeerDBMapper(
        implementation: BeerDBMapper
    ): Mapper<BeerListItem, Beer>

    @Binds
    abstract fun provideBeerMapper(
        implementation: BeerMapper
    ): Mapper<Beer, BeerResponse>

    @Binds
    abstract fun provideAmountMapper(
        implementation: AmountMapper
    ): Mapper<Ingredients.Amount, BeerResponse.IngredientsResponse.AmountResponse>

    @Binds
    abstract fun provideBoilVolumeMapper(
        implementation: BoilVolumeMapper
    ): Mapper<BoilVolume, BeerResponse.BoilVolumeResponse>

    @Binds
    abstract fun provideFermentationMapper(
        implementation: FermentationMapper
    ): Mapper<Method.Fermentation, BeerResponse.MethodResponse.FermentationResponse>

    @Binds
    abstract fun provideHopsMapper(
        implementation: HopsMapper
    ): Mapper<Ingredients.Hops, BeerResponse.IngredientsResponse.HopsResponse>

    @Binds
    abstract fun provideIngredientsMapper(
        implementation: IngredientsMapper
    ): Mapper<Ingredients, BeerResponse.IngredientsResponse>

    @Binds
    abstract fun provideMaltMapper(
        implementation: MaltMapper
    ): Mapper<Ingredients.Malt, BeerResponse.IngredientsResponse.MaltResponse>

    @Binds
    abstract fun provideMashTempMapper(
        implementation: MashTempMapper
    ): Mapper<Method.MashTemp, BeerResponse.MethodResponse.MashTempResponse>

    @Binds
    abstract fun provideMethodMapper(
        implementation: MethodMapper
    ): Mapper<Method, BeerResponse.MethodResponse>

    @Binds
    abstract fun provideTempMapper(
        implementation: TempMapper
    ): Mapper<Method.Temp, BeerResponse.MethodResponse.TempResponse>

    @Binds
    abstract fun provideVolumeMapper(
        implementation: VolumeMapper
    ): Mapper<Volume, BeerResponse.VolumeResponse>
}