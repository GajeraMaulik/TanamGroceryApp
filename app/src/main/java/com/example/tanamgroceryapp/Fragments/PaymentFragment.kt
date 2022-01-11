package com.example.tanamgroceryapp.Fragments

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.example.tanamgroceryapp.Adapter.ProductsAdapter
import com.example.tanamgroceryapp.Interfaces.ItemClickListner
import com.example.tanamgroceryapp.R
import kotlinx.android.synthetic.main.fragment_payment.*

class PaymentFragment : Fragment(){
    private lateinit var paymetMethods:LinearLayout
    private  lateinit var cvCodBtn:CardView
    private  lateinit var codIcon:ImageView
    private  lateinit var cvCreditCardBtn:CardView
    private  lateinit var cdIcon:ImageView
    private  lateinit var cvPaipalBtn:CardView
    private  lateinit var pmIcon:ImageView
    private  lateinit var creditCardView:LinearLayout

    val selected = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?=inflater.inflate(R.layout.fragment_payment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cvCodBtn=view.findViewById(R.id.cvCodBtn)
        cvCreditCardBtn=view.findViewById(R.id.cvCreditCardBtn)
        cvPaipalBtn=view.findViewById(R.id.cvPaipalBtn)
        creditCardView=view.findViewById(R.id.creditCardView)
        paymetMethods=view.findViewById(R.id.paymetMethods)
        codIcon=view.findViewById(R.id.codIcon)
        cdIcon=view.findViewById(R.id.cdIcon)
        pmIcon=view.findViewById(R.id.pmIcon)

        cvCodBtn.setOnClickListener {
            Log.d("card","cod")
            selectedItem(1)
        }
        cvCreditCardBtn.setOnClickListener {
            Log.d("card","Creditcard")
            selectedItem(2)
        }
        cvPaipalBtn.setOnClickListener {
            Log.d("card","Paipal")
            selectedItem(3)
        }

    }

    @SuppressLint("ResourceAsColor")
    fun selectedItem(selected: Int) {
        when (selected) {
            1 -> {
                cvCodBtn.setBackgroundResource(R.drawable.payment_selector)
               cvCreditCardBtn.setBackgroundResource(R.drawable.ic_credit_card_unselected)
                cvPaipalBtn.setBackgroundResource(R.drawable.ic_paypal_unselected)

                creditCardView.visibility=View.INVISIBLE

            }
            2 -> {
                //cvCodBtn.setBackgroundResource(R.drawable.ic_save_money_unseleted)
                //cvCreditCardBtn.setBackgroundResource(R.drawable.payment_selector)
                //cvPaipalBtn.setBackgroundResource(R.drawable.ic_paypal_unselected)

                creditCardView.visibility=View.VISIBLE

            }
            3 -> {
                //cvCodBtn.setBackgroundResource(R.drawable.ic_save_money_unseleted)
                //cvCreditCardBtn.setBackgroundResource(R.drawable.ic_credit_card_unselected)
              //  cvPaipalBtn.setBackgroundResource(R.drawable.payment_selector)

                creditCardView.visibility=View.INVISIBLE
            }
        }

    }



}

private fun ImageView.setImageDrawable(icSaveMoneySeleted: Int) {

}

