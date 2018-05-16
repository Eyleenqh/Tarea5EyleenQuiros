/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea5eyleenquiros;

// Buffer interface specifies methods called by Producer and Consumer.

import Domain.Shuriken;

public interface Buffer
{

    public void set(Shuriken object);  // place value into Buffer

    public Shuriken get();              // return value from Buffer
}

